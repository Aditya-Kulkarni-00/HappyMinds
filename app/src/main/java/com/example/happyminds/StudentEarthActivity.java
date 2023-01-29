package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StudentEarthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_earth);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }
}