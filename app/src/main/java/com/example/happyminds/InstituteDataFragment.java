package com.example.happyminds;

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

public class InstituteDataFragment extends Fragment {
    EditText mobile;
    Button searchBtn, pdfBtn, deleteBtn;
    TextView display, rollno;
    public InstituteDataFragment() {
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
        return inflater.inflate(R.layout.fragment_institute_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mobile = (EditText) requireView().findViewById(R.id.InstituteDataMobile);
        searchBtn = (Button) requireView().findViewById(R.id.InstituteDataSearchButton);
        display = (TextView) requireView().findViewById(R.id.InstituteDataStudentName);
        rollno = (TextView) requireView().findViewById(R.id.InstituteDataRollNo);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database;
                DatabaseReference reference;
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("colleges");
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireContext());
                reference.child(account.getId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        CollegeDetails details = snapshot.getValue(CollegeDetails.class);
                        StudentDetails s = details.getStudentByMobile(mobile.getText().toString(), requireContext());
                        if(s == null){
                            Toast.makeText(requireContext(), "Enter Valid Number!", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            DisplayName(display, s.getName());
                            rollno.setText(s.getRollno());
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

    private void DisplayName(TextView DisplayName, String name){
        DisplayName.setText(name);
    }
}