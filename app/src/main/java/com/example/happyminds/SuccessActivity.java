package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        String target = intent.getStringExtra("target");

        if ("InstituteHome".equals(target)) {
            startActivity(new Intent(getApplicationContext(), InstituteDashboardActivity.class));
        }
    }
}