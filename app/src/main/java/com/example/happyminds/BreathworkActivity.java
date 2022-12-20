package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BreathworkActivity extends AppCompatActivity {
    public static ArrayList<BreathworkDetails> listOfBreathworks = new ArrayList<>();
    Intent i = new Intent(getApplicationContext(), BeginBreathwork.class);
    ImageView b1,b2,b3,b4,b5,b6,b7,b8,b9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathwork);

        addBreathworkDetails();
    }



    void addBreathworkDetails(){

        listOfBreathworks.add(new BreathworkDetails("Relax",
                "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.",
                "Calms your mind",
                "Relieves stress",
                "Helps you fight negativity" ,
                new int[]{4, 0, 6, 0}));
        listOfBreathworks.add(new BreathworkDetails("Balance", "Find a sense of balance of the inner world in your life with the 4-2-4-0 breathing technique.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{4, 2, 4, 0}));
        listOfBreathworks.add(new BreathworkDetails("Reboot", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Focus", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Energise", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Unwind", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Break_time", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("De_stress", "Use this 4-0-6-0 breathing exercise to help slow down a racing heart or when feeling anxious or stressed.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{2, 4, 2, 4}));
        listOfBreathworks.add(new BreathworkDetails("Sleep", "Finding it hard to sleep ? Give the 4-0-8-0 breathing technique a try., and you won’t even realize that you’ve fallen asleep.", "Calms your mind", "Relieves stress", "Helps you fight negativity" , new int[]{4, 0, 8, 0}));

    }
    }