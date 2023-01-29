package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InstituteFAQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_faqactivity);

        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
    }
}