package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SoundCornerActivity extends AppCompatActivity {
    ImageView sound1 , sound2 , sound3 , sound4 , sound5 ,sound6;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_corner);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        sound1 = findViewById(R.id.sound1);
        sound2 = findViewById(R.id.sound2);
        sound3 = findViewById(R.id.sound3);
        sound4 = findViewById(R.id.sound4);
        sound5 = findViewById(R.id.sound5);
        sound6 = findViewById(R.id.sound6);
        sound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext() , PlaySoundActivity.class);
                intent.putExtra("sound" , "forest");
                startActivity(intent);
            }
        });

        sound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext() , PlaySoundActivity.class);
                intent.putExtra("sound" , "rain");
                startActivity(intent);
            }
        });


        sound3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext() , PlaySoundActivity.class);
                intent.putExtra("sound" , "chirp");
                startActivity(intent);
            }
        });


        sound4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext() , PlaySoundActivity.class);
                intent.putExtra("sound" , "waves");
                startActivity(intent);
            }
        });

        sound5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext() , PlaySoundActivity.class);
                intent.putExtra("sound" , "forest");

                startActivity(intent);
            }
        });
        sound6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext() , PlaySoundActivity.class);
                intent.putExtra("sound" , "forest");
                startActivity(intent);
            }
        });

    }
}