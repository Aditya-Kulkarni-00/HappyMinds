package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentTestActivity extends AppCompatActivity {
    Button startTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_test);


        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        startTest = findViewById(R.id.StudentStartTest);
        Intent intent2 = getIntent();
        String mobile = intent2.getStringExtra("mobile");


        startTest.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext() , StudentStartTest.class);
            intent.putExtra("mobile", mobile);
            startActivity(intent);
            finish();
        });
    }
}