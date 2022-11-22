package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Calendar;
import java.util.Date;

public class GuestHomeScreenActivity extends AppCompatActivity {
    Boolean isPlaying;
    String username;
    TextView user, greeting;
    ImageView changeSong , Logout ;
    Button SoundCorner, BreathWork, Experts , Helpline;
    CarouselView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home_screen);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        user=  (TextView) findViewById(R.id.GuestHomeScreenUsername);
        greeting = (TextView) findViewById(R.id.GuestHomeGreeting);
        username = intent.getStringExtra("name");
        user.setText(username);

        SoundCorner = (Button) findViewById(R.id.GuestHomeSoundCorner);
        BreathWork  = (Button) findViewById(R.id.GuestHomeBreathwork);
        Experts = (Button) findViewById(R.id.GuestHomeExperts);
        Helpline = (Button)findViewById(R.id.GuestHomeHelpline);

        view = (CarouselView) findViewById(R.id.BackgroundQuotes);
        int[] backgroundQuoteImages = {R.drawable.backgroundquotes1, R.drawable.backgroundquotes2,R.drawable.backgroundquotes3,  R.drawable.backgroundquotes4,R.drawable.backgroundquotes5};

        greeting(greeting);
        Intent musicIntent = new Intent(getApplicationContext(), BackgroundMusicService.class);
        musicIntent.putExtra("musicName", "waves");
        startService(musicIntent);

        SoundCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), SoundCornerActivity.class);
                startActivity(intent1);

            }
        });

        view.setPageCount(backgroundQuoteImages.length);
        view.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(backgroundQuoteImages[position]);
            }
        });

    }

    private void greeting(TextView greetingView ){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour > 0 && hour < 12){
            greetingView.setText("Good Morning");
        }else if(hour > 12 && hour < 17){
            greetingView.setText("Good Afternoon");
        }
        if(hour > 17 && hour < 20){
            greetingView.setText("Good Evening");
        }else {
            greetingView.setText("Good Night");
        }


    }
}