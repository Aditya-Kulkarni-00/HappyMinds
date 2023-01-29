package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ExpertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);


        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
}