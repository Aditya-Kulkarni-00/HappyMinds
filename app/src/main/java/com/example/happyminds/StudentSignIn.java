package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentSignIn extends AppCompatActivity {
    TextView forgotPassword;
    EditText mob , password;
    ProgressDialog dialog;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        forgotPassword = findViewById(R.id.StudentForgotPassword);
        mob = findViewById(R.id.StudentSignInMobile);
        password = findViewById(R.id.StudentSignInPassword);
        login = findViewById(R.id.StudentSignInLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(StudentSignIn.this);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setTitle("Fetching from database");
                dialog.setMessage("Please wait while we load the data");
                dialog.show();
                String m = mob.getText().toString();
                String p = password.getText().toString();
                searchForStudent(m , p);
            }
        });

        forgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), StudentForgotPassword.class);
            startActivity(intent);
            finish();
        });
    }


    private void searchForStudent(String  m , String p){
        FirebaseDatabase database;
        DatabaseReference reference;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("colleges");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean foundInDatabase = false;
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    CollegeDetails collegeDetails = snapshot1.getValue(CollegeDetails.class);
                    StudentDetails details = null;
                    if (collegeDetails != null) {
                        details = collegeDetails.getStudentByMobile(m, getApplicationContext());
                    }
                    if(details != null){
                        foundInDatabase = true;
                        dialog.dismiss();
                        if (!details.getHasSetPassword()){
                            Toast.makeText(StudentSignIn.this, "Password Not Set ! Please Set it First", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            if(!p.equals(details.getPassword())){
                                Toast.makeText(StudentSignIn.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent;
                                if(details.getTakenTest()){
                                    intent = new Intent(getApplicationContext(), StudentDashboardActivity.class);
                                }else {
                                    intent = new Intent(getApplicationContext(), StudentTestActivity.class);
                                }
                                intent.putExtra("mobile" , m);
                                startActivity(intent);

                                finish();
                            }
                        }
                    }
                }

                if(!foundInDatabase){
                    Toast.makeText(getApplicationContext(), "Please register your number in college", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error Connecting to Database !", Toast.LENGTH_SHORT).show();
            }
        });


    }
}