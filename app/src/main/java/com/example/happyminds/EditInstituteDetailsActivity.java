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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditInstituteDetailsActivity extends AppCompatActivity {
    Spinner colleges,states,cities;
    EditText address, phone, telephone, pinCode;
    Button submit;
    FirebaseDatabase database;
    DatabaseReference reference;
    GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_institute_details);


        colleges = (Spinner) findViewById(R.id.EditInstituteDetailsCollege);
        states = (Spinner) findViewById(R.id.EditInstituteDetailsStates);
        cities = (Spinner) findViewById(R.id.EditInstituteDetailsCities);

        address = (EditText) findViewById(R.id.EditInstituteDetailsAddress);
        phone= (EditText) findViewById(R.id.EditInstituteDetailsPhone);
        telephone = (EditText) findViewById(R.id.EditInstituteDetailsTelephone);
        pinCode = (EditText) findViewById(R.id.EditInstituteDetailsPinCode);

        submit = (Button) findViewById(R.id.EditInstituteDetailsSubmit);

        ArrayAdapter<CharSequence> collegeAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.colleges, android.R.layout.simple_spinner_item);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> statesAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.states, android.R.layout.simple_spinner_item);
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> citiesAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.cities, android.R.layout.simple_spinner_item);
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        colleges.setAdapter(collegeAdapter);
        states.setAdapter(statesAdapter);
        cities.setAdapter(citiesAdapter);

        submit.setOnClickListener(view -> {
            if(!validate()){
                Toast.makeText(EditInstituteDetailsActivity.this, "Please Enter all the valid Details", Toast.LENGTH_SHORT).show();
            }else{
                changeToDataBase();
                Intent intent = new Intent(getApplicationContext(), InstituteDashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validate(){
        if(address.getText().toString().equals("")){
            return false;
        }

        if(phone.getText().toString().equals("") || phone.getText().toString().length() < 10){
            return false;
        }

        if(telephone.getText().toString().equals("") || telephone.getText().toString().length() < 10){
            return false;
        }
        if(pinCode.getText().toString().equals("") || pinCode.getText().toString().length() < 6) {
            return false;
        }
        return true;
    }

    private void changeToDataBase(){
        account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("colleges");

        if (account != null) {

            String CollegeName = colleges.getSelectedItem().toString();
            String CollegeEmail = account.getEmail();
            String CollegeAddress = address.getText().toString();
            String CollegePinCode = pinCode.getText().toString();
            String CollegePhone = phone.getText().toString();
            String CollegeTelephone = telephone.getText().toString();
            String CollegeCity = cities.getSelectedItem().toString();
            String CollegeState = states.getSelectedItem().toString();

            String id = account.getId();

            if(id != null){
                reference.child(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        CollegeDetails details = snapshot.getValue(CollegeDetails.class);
                        if(details !=null){
                            details.setName(CollegeName);
                            details.setEmail(CollegeEmail);
                            details.setAddress(CollegeAddress);
                            details.setPincode(CollegePinCode);
                            details.setPhone(CollegePhone);
                            details.setTelephone(CollegeTelephone);
                            details.setCity(CollegeCity);
                            details.setState(CollegeState);

                            reference.child(id).setValue(details, (error, ref) -> Toast.makeText(EditInstituteDetailsActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
    }
}