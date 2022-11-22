package com.example.happyminds;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class InstituteUploadFragment extends Fragment {
    Button StudentDownloadTemplateButton, StudentUploadDetailsButton, StudentDetailsSubmitButton;
    TextView StudentDetailsFileChosen;
    FirebaseDatabase database;
    DatabaseReference reference;
    GoogleSignInAccount account;
    // request codes
    private final int WRITE_CSV = 1;
    private final int READ_CSV = 0;

    public InstituteUploadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institute_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StudentDetailsFileChosen = (TextView) requireView().findViewById(R.id.StudentDetailsFileChosen);

        StudentDownloadTemplateButton= (Button) requireView().findViewById(R.id.StudentDownloadTemplateButton);

        StudentUploadDetailsButton = (Button) requireView().findViewById(R.id.StudentUploadDetailsButton);

        StudentUploadDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFile();
            }
        });

        StudentDownloadTemplateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                createFile();
            }
        });
    }

    public void openFile(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_CSV);
    }


    private void UploadStudentDetails(Uri uri, String UID){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("colleges").child(UID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<StudentDetails> details = readFile(uri);
                CollegeDetails details1 = snapshot.getValue(CollegeDetails.class);
                if(details!=null){
                    for (int i = 0; i < details.size(); i++) {
                        if (details1 != null) {
                            details1.setStudents(details);
                        }
                    }
                }else{
                    Toast.makeText(requireContext(), "Failed to Read CSV", Toast.LENGTH_SHORT).show();
                }
                reference.setValue(details1, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(requireContext(), "Upload Successfull to Database!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "Failed to Connect to Database", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private ArrayList<StudentDetails> readFile(Uri uri) {
        try {
            ArrayList<StudentDetails> list = new ArrayList<>();
            ParcelFileDescriptor descriptor = requireActivity().getContentResolver().openFileDescriptor(uri, "r");
            FileReader fileReader = new FileReader(descriptor.getFileDescriptor());
            CSVReader reader = new CSVReaderBuilder(fileReader).withSkipLines(1).withKeepCarriageReturn(false).build();
            String[] nextRecord = reader.readNext();
            while (nextRecord!=null){

                    //"Roll No", "Name", "Age", "Mobile"
                    String Rollno = nextRecord[0];
                    String Name = nextRecord[1];
                    String Age = nextRecord[2];
                    String Mobile = nextRecord[3];

                    StudentDetails details = new StudentDetails(Mobile, Rollno, Age, Name);
                    list.add(details);

                    nextRecord = reader.readNext();
            }
            reader.close();
            descriptor.close();

            return list;
        } catch (IOException e) {
            Toast.makeText(requireContext(), "Errorrr!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        }
    }

    public void createFile(){
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/csv");
        intent.putExtra(Intent.EXTRA_TITLE, "template.csv");

        startActivityForResult(intent, WRITE_CSV);
    }

    public void DownloadStudentTemplateDemo(Context context, Uri uri){
        try {
            ParcelFileDescriptor descriptor = requireActivity().getContentResolver().openFileDescriptor(uri, "w");

            FileWriter outPutFile = new FileWriter(descriptor.getFileDescriptor());
            CSVWriter writer = new CSVWriter(outPutFile);

            // adding header

            String[] header = {"Roll No", "Name", "Age", "Mobile"};
            writer.writeNext(header);

            // closing writer

            writer.close();
            descriptor.close();

        }catch (IOException exception){

            Toast.makeText(context, "File Write Failed !", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileNameFromUri(Uri uri){

        try{
            Cursor cursor = requireActivity().getContentResolver().query(uri, null, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                @SuppressLint("Range") String filename = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                cursor.close();
                return  filename;
            }else{
                return null;
            }
        }catch (Exception e){
            Toast.makeText(requireContext(), "Failed to Fetch Name of the File", Toast.LENGTH_SHORT).show();
            return null;
        }

    }

    private void setFileName(TextView textView , String content){
        textView.setText(content);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == READ_CSV && resultCode == Activity.RESULT_OK){
            Uri uri = null;
            if(data !=null){
                uri = data.getData();
                account = GoogleSignIn.getLastSignedInAccount(requireContext());
                if (account != null) {
                    UploadStudentDetails(uri, account.getId());
                    setFileName(StudentDetailsFileChosen,getFileNameFromUri(uri));
                }
            }
        }

        if(requestCode == WRITE_CSV && resultCode == Activity.RESULT_OK){
            Uri uri = null;
            if(data !=null){
                uri = data.getData();
                DownloadStudentTemplateDemo(requireContext(), uri);

            }
        }
    }
}