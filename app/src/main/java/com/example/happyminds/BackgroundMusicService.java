package com.example.happyminds;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundMusicService extends Service {

    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(getApplicationContext(), R.raw.forest);
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        player.stop();
        return super.stopService(name);
    }

    public void changeSong (int MusicResourceId){

        if(player !=null){
            if(player.isPlaying()){
                player.stop();
            }
            player.release();
        }

        player =  MediaPlayer.create(getApplicationContext(), MusicResourceId);
        player.start();

    }

    public void changeVolume(float volume){
        if (player !=null){
            player.setVolume(volume, volume);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();

    }
}
