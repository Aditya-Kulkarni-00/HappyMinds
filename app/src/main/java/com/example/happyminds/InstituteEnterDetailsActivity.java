package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InstituteEnterDetailsActivity extends AppCompatActivity {
    Spinner colleges,states,cities;
    EditText address, phone, telephone, pinCode;
    Button submitButton;
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
                    Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
                    intent.putExtra("target", "InstituteHome");
                    startActivity(intent);
                }
            }
        });

    }

    boolean validate(){
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
}