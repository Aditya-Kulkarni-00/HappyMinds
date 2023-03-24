package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivitySessionEasy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_easy);

        Button relaxation  = findViewById(R.id.relaxation_easy);
        relaxation.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext() , ActivityRelaxationEasy.class);
            startActivity(intent);
        });
    }
}