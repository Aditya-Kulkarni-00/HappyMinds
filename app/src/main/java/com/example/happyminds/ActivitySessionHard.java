package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ActivitySessionHard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_hard);

        Button relaxation  = findViewById(R.id.relaxation_hard);
        relaxation.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext() , ActivityRelaxationHard.class);
            startActivity(intent);
        });
    }
}