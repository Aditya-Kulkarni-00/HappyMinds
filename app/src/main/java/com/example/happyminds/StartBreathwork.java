package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class StartBreathwork extends AppCompatActivity {
    BreathworkDetails det;
    TextView counter , inhaleexhale;
    String b;
    int[] pattern;
    Handler handler = new Handler();
    int index = 0;
    int count = 0;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_breathwork);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        b = intent.getStringExtra("breathworkactivity");

        counter = findViewById(R.id.sbwcounter);
        inhaleexhale = findViewById(R.id.sbwinex);
        findDetails(b);

        pattern = det.getPattern();


        runnable = new Runnable() {
            @Override
            public void run() {
                if (index % 2 == 0) {
                    inhaleexhale.setText("Inhale");
                } else {
                    inhaleexhale.setText("Exhale");
                }

                count++;
                counter.setText(String.valueOf(count));
                if (pattern[index] == count) {
                    if (index >= 3) {
                        handler.removeCallbacks(this);

                        Intent intent2 = new Intent(getApplicationContext() , BreathworkActivity.class);
                        startActivity(intent2);
                        finish();
                    }else{
                        index++;
                        count = 0;
                    }

                }
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "YOu Cannot Back here !", Toast.LENGTH_SHORT).show();
        return;

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