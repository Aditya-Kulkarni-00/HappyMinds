package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BeginBreathwork extends AppCompatActivity {
    TextView name , description , benefit , benefit2 , benefit3 ;
    BreathworkDetails det;
    String b;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_begin_breathwork);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        b = intent.getStringExtra("breathworkactivity");

        name = findViewById(R.id.beginbwtitle);
        description = findViewById(R.id.beginbwdescription);
        benefit = findViewById(R.id.beginbwbenefit1);
        benefit2 = findViewById(R.id.beginbwbenefit2);
        benefit3 = findViewById(R.id.beginbwbenefit3);
        btn = findViewById(R.id.beginbwbutton);

        findDetails(b);

        name.setText(det.getName());
        description.setText(det.getDirections());
        benefit.setText(det.getBenefit1());
        benefit2.setText(det.getBenefit2());
        benefit3.setText(det.getBenefit3());
        btn.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext() , StartBreathwork.class);
            intent1.putExtra("breathworkactivity" , b);
            startActivity(intent1);
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    void findDetails(String bname){
        boolean flag = false;
        for (BreathworkDetails listOfBreathwork : BreathworkActivity.listOfBreathworks) {
            if (bname.equals(listOfBreathwork.getName())) {
                flag = true;
                det = listOfBreathwork;
                break;
            }
        }

        if(!flag){
            finish();
        }
    }
}