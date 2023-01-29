package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BreathworkActivity extends AppCompatActivity {
    public static ArrayList<BreathworkDetails> listOfBreathworks = new ArrayList<>();
    Intent i;
    ImageView b1,b2,b3,b4,b5,b6,b7,b8,b9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathwork);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        i = new Intent(getApplicationContext(), BeginBreathwork.class);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        addBreathworkDetails();

        b1.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "Relax");
            startActivity(i);
            finish();
        });

        b2.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "Balance");
            startActivity(i);
            finish();
        });

        b3.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "Reboot");
            startActivity(i);
            finish();
        });

        b4.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "Focus");
            startActivity(i);
            finish();
        });

        b5.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "Energise");
            startActivity(i);
            finish();
        });

        b6.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "Unwind");
            startActivity(i);
        });

        b7.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "Break_time");
            startActivity(i);
            finish();
        });

        b8.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "De_stress");
            startActivity(i);
            finish();
        });

        b9.setOnClickListener(view -> {
            i.putExtra("breathworkactivity" , "Sleep");
            startActivity(i);
            finish();
        });
    }



    void addBreathworkDetails(){

        listOfBreathworks.add(new BreathworkDetails("Relax",
                "Use this 4-2-6-2 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.",
                "Calms your mind",
                "Relieves stress",
                "Helps you fight negativity" ,
                new int[]{4, 2, 6, 2}));
        listOfBreathworks.add(new BreathworkDetails("Balance", "Find a sense of balance of the inner world in your life with the 4-2-4-0 breathing technique.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{4, 2, 6, 2}));
        listOfBreathworks.add(new BreathworkDetails("Reboot", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Focus", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Energise", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Unwind", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Break_time", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("De_stress", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Sleep", "Finding it hard to sleep ? Give the 4-0-8-0 breathing technique a try., and you won’t even realize that you’ve fallen asleep.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{4, 2, 6, 2}));

    }

    @Override
    public void onBackPressed() {
    super.onBackPressed();
    }
}