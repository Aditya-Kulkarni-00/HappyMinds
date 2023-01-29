package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentForgotPassword extends AppCompatActivity {
    EditText phone , password , confirm;
    Button submit;
    ProgressDialog dialog;
    FirebaseDatabase database;
    DatabaseReference reference;
    StudentDetails details = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_forgot_password);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        phone = findViewById(R.id.StudentForgotPasswordPhone);
        password = findViewById(R.id.StudentForgotPasswordPassword);
        confirm = findViewById(R.id.StudentForgotPasswordConfirm);
        dialog = new ProgressDialog(StudentForgotPassword.this);
        submit = findViewById(R.id.StudentForgotPasswordSubmit);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Fetching from database");
        dialog.setMessage("Please wait while we load the data");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = phone.getText().toString();
                String pass = password.getText().toString();
                String conf = confirm.getText().toString();

                if(mobile.equals("") || pass.equals("")||conf.equals("")){
                    Toast.makeText(StudentForgotPassword.this, "Enter Valid Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!pass.equals(conf)){
                    Toast.makeText(StudentForgotPassword.this, "Password is not equal to confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }

                dialog.show();
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("colleges");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean foundInDatabase = false;
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            CollegeDetails collegeDetails = snapshot1.getValue(CollegeDetails.class);
                            details = null;
                            if (collegeDetails != null) {
                                details = collegeDetails.getStudentByMobile(mobile, getApplicationContext());
                            }
                            if(details != null){
                                foundInDatabase = true;

                                if (!details.getHasSetPassword()){
                                    Toast.makeText(getApplicationContext(), "Password Not Set ! Please Set it First", Toast.LENGTH_SHORT).show();
                                    return;
                                }else {

                                        details.setPassword(pass);
                                        reference.child(details.getCollegeUID()).setValue(collegeDetails, (error, ref) -> {
                                            dialog.dismiss();
                                            Intent intent;
                                            if(details.getTakenTest()){
                                                intent = new Intent(getApplicationContext(), StudentDashboardActivity.class);
                                            }else {
                                                intent = new Intent(getApplicationContext(), StudentTestActivity.class);
                                            }
                                            intent.putExtra("mobile" , mobile);
                                            startActivity(intent);
                                        });


                                        finish();
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
        });
    }
}