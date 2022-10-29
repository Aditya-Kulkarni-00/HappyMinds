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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InstituteEnterDetailsActivity extends AppCompatActivity {
    Spinner colleges,states,cities;
    EditText address, phone, telephone, pinCode;
    Button submitButton, signOutButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_enter_details);

        colleges = (Spinner) findViewById(R.id.spinner_colleges);
        states = (Spinner) findViewById(R.id.spinner_states);
        cities = (Spinner) findViewById(R.id.spinner_cities);

        address = (EditText) findViewById(R.id.insituteEnterDetailsAddress);
        phone= (EditText) findViewById(R.id.insituteEnterDetailsMobile);
        telephone = (EditText) findViewById(R.id.insituteEnterDetailsTelephone);
        pinCode = (EditText) findViewById(R.id.insituteEnterDetailsPinCode);


        submitButton = (Button) findViewById(R.id.insituteEnterDetailsSubmit);
        signOutButton = (Button) findViewById(R.id.insituteEnterDetailsSignOut);

        ArrayAdapter<CharSequence> collegeAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.colleges, android.R.layout.simple_spinner_item);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> statesAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.states, android.R.layout.simple_spinner_item);
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> citiesAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.cities, android.R.layout.simple_spinner_item);
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        colleges.setAdapter(collegeAdapter);
        states.setAdapter(statesAdapter);
        cities.setAdapter(citiesAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate()){
                    Toast.makeText(InstituteEnterDetailsActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }else {
                    addToDatabase();
                    Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
                    intent.putExtra("target", "InstituteHome");
                    startActivity(intent);
                }
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstituteSignInActivity.googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getApplicationContext(), InstituteSignInActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    private boolean validate(){
        if(address.getText().toString().equals("")){
            return false;
        }

        if(phone.getText().toString().equals("")){
            return false;
        }

        if(telephone.getText().toString().equals("")){
            return false;
        }
        if(pinCode.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    private void addToDatabase(){
        account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("colleges");

        if (account != null){

            String CollegeName = colleges.getSelectedItem().toString();
            String CollegeEmail = account.getEmail();
            String CollegeAddress = address.getText().toString();
            String CollegePinCode = pinCode.getText().toString();
            String CollegePhone = phone.getText().toString();
            String CollegeTelephone = telephone.getText().toString();
            String CollegeCity = cities.getSelectedItem().toString();
            String CollegeState = states.getSelectedItem().toString();

            String id = account.getId();

            CollegeDetails details = new CollegeDetails(CollegeName, CollegeEmail, CollegeAddress, CollegePinCode, CollegePhone, CollegeTelephone, CollegeCity, CollegeState);
//            details.addStudent("7020325304");
            if(id !=null){
                reference.child(id).setValue(details, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(InstituteEnterDetailsActivity.this, "Inserted Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        }


    }
}