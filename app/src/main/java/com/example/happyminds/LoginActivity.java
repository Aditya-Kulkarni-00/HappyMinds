package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button instituteLogin, guestLogin, studentLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        instituteLogin = (Button) findViewById(R.id.instituteLogin);
        guestLogin = (Button) findViewById(R.id.guestLogin);
        studentLogin = (Button) findViewById(R.id.studentLogin);



        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        instituteLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InstituteSignInActivity.class);
                startActivity(intent);
            }
        });

        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), GuestLoginActivity.class);
                startActivity(intent);
            }
        });

        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}