package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
}