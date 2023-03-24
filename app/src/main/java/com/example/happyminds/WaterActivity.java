package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class WaterActivity extends AppCompatActivity {
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        player = MediaPlayer.create(getApplicationContext() , R.raw.chirp);
        player.start();
    }

    @Override
    public void onBackPressed() {
        if(player.isPlaying()){
            player.stop();
        }

        player.release();
        super.onBackPressed();
    }
}