package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class PlaySoundActivity extends AppCompatActivity {
    ImageView exit;
    MediaPlayer player;
    Intent soundName;
    ImageView pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sound);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        exit = findViewById(R.id.exit);
        pause = findViewById(R.id.PlaySoundActiviyPause);

        pause.setOnClickListener(view -> {
            if(player.isPlaying()){
                player.pause();
            }else {
                player.start();
            }

        });
        soundName = getIntent();
        String sound = soundName.getStringExtra("sound");

        if(sound.equals("chirp")){
            player = MediaPlayer.create(getApplicationContext() , R.raw.chirp);
        }else if(sound.equals("forest")){
            player = MediaPlayer.create(getApplicationContext() , R.raw.forest);
        }else if(sound.equals("rain")){
            player = MediaPlayer.create(getApplicationContext() , R.raw.rain);
        }else {
            player = MediaPlayer.create(getApplicationContext() , R.raw.waves);
        }

        player.start();
        exit.setOnClickListener(view -> onBackPressed());




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