package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class StudentSetPasswordActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;

    Button createStudent;

    EditText password , confirmPassword;
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

        createStudent = findViewById(R.id.StudentSetPasswordCreate);
        password = findViewById(R.id.StudentSetPassword);
        confirmPassword = findViewById(R.id.StudentSetPasswordConfirm);

        createStudent.setOnClickListener(view -> {
            String pwd = password.getText().toString();

            if(!pwd.equals(confirmPassword.getText().toString())){
                Toast.makeText(StudentSetPasswordActivity.this, "Reenter Password Correctly", Toast.LENGTH_SHORT).show();
            }else {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            CollegeDetails collegeDetails = snapshot1.getValue(CollegeDetails.class);
                            StudentDetails details = null;
                            if (collegeDetails != null) {
                                details = collegeDetails.getStudentByMobile(mobile, getApplicationContext());
                            }
                            if (details != null) {
                                if (details.getHasSetPassword()) {
                                    Toast.makeText(StudentSetPasswordActivity.this, "Password Already Set", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                details.setPassword(pwd);
                                details.setHasSetPassword(true);


                                DatabaseReference reference2 = database.getReference("colleges").child(details.getCollegeUID());
                                reference2.setValue(collegeDetails, (error, ref) -> {
                                    Toast.makeText(StudentSetPasswordActivity.this, "Password Successfully Set", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(getApplicationContext() , StudentEnterDetails.class);
                                    intent1.putExtra("mobile" , mobile);
                                    startActivity(intent1);
                                    finishAfterTransition();
                                });


                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(StudentSetPasswordActivity.this, "Error in DB | Try Again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}