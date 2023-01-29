package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentLoginActivity extends AppCompatActivity {
    Button signInButton , signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        signInButton = (Button) findViewById(R.id.StudentSignInButton);
        signUpButton = (Button) findViewById(R.id.StudentSignUpButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentSignIn.class);
                startActivity(intent);
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentSignUp.class);
                startActivity(intent);
                finish();
            }
        });



    }
}