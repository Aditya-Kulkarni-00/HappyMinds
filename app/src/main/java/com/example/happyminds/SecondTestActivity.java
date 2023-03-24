package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SecondTestActivity extends AppCompatActivity {
    Button startTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_test);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        startTest = findViewById(R.id.StudentStartReTest);
        Intent intent2 = getIntent();
        String mobile = intent2.getStringExtra("mobile");

        startTest.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext() , SecondTestStartActivity.class);
            intent.putExtra("mobile", mobile);
            startActivity(intent);
            finish();
        });
    }
}