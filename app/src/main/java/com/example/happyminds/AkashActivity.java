package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AkashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akash);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
}