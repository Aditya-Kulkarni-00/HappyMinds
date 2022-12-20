package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentSetPasswordActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_set_password);
        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("colleges");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    CollegeDetails collegeDetails = snapshot1.getValue(CollegeDetails.class);
                    StudentDetails details = null;
                    if (collegeDetails != null) {
                        details = collegeDetails.getStudentByMobile(mobile, getApplicationContext());
                    }
                    if(details != null){
                        details.setPassword(null);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}