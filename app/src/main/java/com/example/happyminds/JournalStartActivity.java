package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JournalStartActivity extends AppCompatActivity {
    String mobile;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_start);
        mobile = getIntent().getStringExtra("mobile");
        button = findViewById(R.id.CheckInStartButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckInStateActivity.class);
                intent.putExtra("mobile" , mobile);
                startActivity(intent);
                finish();
            }
        });

    }
}