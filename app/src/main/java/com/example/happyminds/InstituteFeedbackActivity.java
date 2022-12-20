package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InstituteFeedbackActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_feedback);

        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }

        btn = (Button) findViewById(R.id.submitfeedback);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InstituteFeedbackActivity.this, "Thank you for feedback", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}