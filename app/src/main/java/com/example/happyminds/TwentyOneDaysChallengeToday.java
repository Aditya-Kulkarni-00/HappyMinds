package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TwentyOneDaysChallengeToday extends AppCompatActivity {
    TextView day;
    EditText challenge;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_one_days_challenge_today);

        Intent intent = getIntent();
        String challenge2 = intent.getStringExtra("challenge");
        int day2 = intent.getIntExtra("day" , 0);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }


        day = findViewById(R.id.TodayDay);
        day.setText(String.valueOf(day2));
        challenge = findViewById(R.id.TodayChallenge);
        challenge.setText(challenge2);
        submit = findViewById(R.id.TodaySubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please Complete the Challenge", Toast.LENGTH_SHORT).show();
    }
}