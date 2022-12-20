package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class StudentSignUp extends AppCompatActivity {
    EditText phone;
    TextView displayName;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        phone = (EditText) findViewById(R.id.StudentSignUpMobile);
        displayName = (TextView) findViewById(R.id.StudentSignUpDisplayName);
        next = (Button) findViewById(R.id.StudentSignUpNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateMobile()){
                    return;
                }else {
                    searchForStudentMobile();
                }
            }
        });

    }


    private boolean validateMobile(){
        return (phone.getText().toString().length() > 9);
    }

    private void searchForStudentMobile(){
        FirebaseDatabase database;
        DatabaseReference reference;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("colleges");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean foundInDatabase = false;
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    CollegeDetails collegeDetails = snapshot1.getValue(CollegeDetails.class);
                    StudentDetails details = null;
                    if (collegeDetails != null) {
                        details = collegeDetails.getStudentByMobile(phone.getText().toString(), getApplicationContext());
                    }
                    if(details != null){
                        displayName.setText(details.getName());
                        foundInDatabase = true;
                        Intent intent = new Intent(getApplicationContext(), StudentCreatePassword.class);
                        intent.putExtra("mobile", phone.getText().toString());
                        startActivity(intent);
                    }
                }
                
                if(!foundInDatabase){
                    Toast.makeText(StudentSignUp.this, "Please register your number in college", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentSignUp.this, "Error Connecting to Database !", Toast.LENGTH_SHORT).show();
            }
        });


    }
}