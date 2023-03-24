package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentEnterDetails extends AppCompatActivity {
    Spinner city , state , reason ;
    EditText pincode;
    Button submit;
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enter_details);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        mobile = getIntent().getStringExtra("mobile");

        city= findViewById(R.id.studentEnterDetailsCities);
        state = findViewById(R.id.studentEnterDetailsStates);
        reason = findViewById(R.id.studentEnterDetailsReason);
        pincode = findViewById(R.id.StudentEnterDetailsPinCode);
        submit = findViewById(R.id.StudentEnterDetailsSubmit);
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(getApplicationContext() , R.array.cities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(getApplicationContext() , R.array.states, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> reasonAdapter = ArrayAdapter.createFromResource(getApplicationContext() , R.array.reason, android.R.layout.simple_spinner_item);
        reasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        city.setAdapter(cityAdapter);
        state.setAdapter(stateAdapter);
        reason.setAdapter(reasonAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pincode.getText().toString().equals("")){
                    Toast.makeText(StudentEnterDetails.this, "Please Enter PinCode", Toast.LENGTH_SHORT).show();
                }else {
                    searchForStudentMobile(city.getSelectedItem().toString(), state.getSelectedItem().toString(), reason.getSelectedItem().toString() , pincode.getText().toString());
                }
            }
        });

    }

    private void searchForStudentMobile(String city , String state , String reason , String pinCode){
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
                        details = collegeDetails.getStudentByMobile(mobile, getApplicationContext());
                    }
                    if(details != null){
                        foundInDatabase = true;
                        details.setCity(city);
                        details.setState(state);
                        details.setReason(reason);
                        details.setPincode(pinCode);


                        reference.child(details.getCollegeUID()).setValue(collegeDetails, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Intent intent = new Intent(getApplicationContext(), StudentTestActivity.class);
                                intent.putExtra("mobile", mobile);
                                startActivity(intent);
                            }
                        });


                    }
                }

                if(!foundInDatabase){
                    Toast.makeText(StudentEnterDetails.this, "Please register your number in college", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentEnterDetails.this, "Error Connecting to Database !", Toast.LENGTH_SHORT).show();
            }
        });


    }
}