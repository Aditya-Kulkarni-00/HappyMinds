package com.example.happyminds;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class InstituteDataFragment extends Fragment {
    EditText mobile;
    Button searchBtn, pdfBtn, deleteBtn;
    TextView display, rollno;
    Context context;
    AlertDialog.Builder builder;
    Activity activity;

    public InstituteDataFragment() {
        // Required empty public constructor
    }

    public InstituteDataFragment(Context mcontext) {
        // Required empty public constructor
        this.context = mcontext;
    }

    public InstituteDataFragment(Context applicationContext, Activity instituteDashboardActivity) {
        context = applicationContext;
        activity = instituteDashboardActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institute_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mobile = requireView().findViewById(R.id.InstituteDataMobile);
        searchBtn = requireView().findViewById(R.id.InstituteDataSearchButton);
        display = requireView().findViewById(R.id.InstituteDataStudentName);
        rollno = requireView().findViewById(R.id.InstituteDataRollNo);
        deleteBtn = requireView().findViewById(R.id.InstituteDataDeleteBtn);

        builder = new AlertDialog.Builder(activity);
        builder.setMessage("Do you really want to delete the record!");
        builder.setTitle("Warning !!!!");
        builder.setCancelable(false);


        searchBtn.setOnClickListener(view1 -> {
            FirebaseDatabase database;
            DatabaseReference reference;
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("colleges");
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
            if (account != null) {
                reference.child(Objects.requireNonNull(account.getId())).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        CollegeDetails details = snapshot.getValue(CollegeDetails.class);
                        StudentDetails s = details != null ? details.getStudentByMobile(mobile.getText().toString(), context) : null;
                        if (s == null) {
                            Toast.makeText(context, "Enter Valid Number!", Toast.LENGTH_SHORT).show();
                        } else {
                            DisplayName(display, s.getName());
                            rollno.setText(s.getRollno());
                            deleteBtn.setOnClickListener(view2 -> {

                                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                                    details.getStudents().remove(s);

                                    DatabaseReference reference2 = database.getReference("colleges");

                                    reference2.child(account.getId()).setValue(details, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                            rollno.setText("");
                                            DisplayName(display , "");
                                            mobile.setText("");
                                            dialogInterface.cancel();
                                        }
                                    });
                                });

                                builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());

                                builder.show();
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(requireContext(), "Database Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void DisplayName(TextView DisplayName, String name) {
        DisplayName.setText(name);
    }
}