package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InstitutueAboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutue_about_us);


        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
    }
}