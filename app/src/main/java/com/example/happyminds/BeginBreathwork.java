package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BeginBreathwork extends AppCompatActivity {
    TextView name , description , benefit , benefit2 , benefit3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean flag = false;
        setContentView(R.layout.activity_begin_breathwork);
        Intent intent = getIntent();
        String bname = intent.getStringExtra("breathworkactivity");

        for (BreathworkDetails listOfBreathwork : BreathworkActivity.listOfBreathworks) {
            if (bname.equals(listOfBreathwork.getName())) {
                flag = true;
                break;
            }
        }

        if(!flag){
            finish();
        }

    }
}