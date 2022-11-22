package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GuestLoginActivity extends AppCompatActivity {
    Button signIn;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        signIn = (Button) findViewById(R.id.GuestLoginActivitySignIn);
        username = (EditText) findViewById(R.id.GuestLoginActivityUserName);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();

                if(name.equals("")){
                    Toast.makeText(GuestLoginActivity.this, "Enter Your Name to Continue!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), GuestHomeScreenActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}