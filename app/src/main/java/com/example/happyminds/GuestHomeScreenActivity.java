package com.example.happyminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
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
    ImageView changeSong , Logout , book1, book2, book3 , book4 ;
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
        Logout = findViewById(R.id.guestlogout);
        book1 = (ImageView) findViewById(R.id.book1);
        book2 = (ImageView) findViewById(R.id.book2);
        book3 = (ImageView) findViewById(R.id.book3);
        book4 = (ImageView) findViewById(R.id.book4);
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

        BreathWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), BreathworkActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        view.setPageCount(backgroundQuoteImages.length);
        view.setImageListener((position, imageView) -> imageView.setImageResource(backgroundQuoteImages[position]));


        book1.setOnClickListener(view -> {
            Intent bookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amzn.to/3fd8PHk"));
            startActivity(bookIntent);
        });

        book2.setOnClickListener(view -> {
            Intent bookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amzn.to/3TKeZ0L"));
            startActivity(bookIntent);
        });

        book3.setOnClickListener(view -> {
            Intent bookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amzn.to/3zrFShP"));
            startActivity(bookIntent);
        });

        book4.setOnClickListener(view -> {
            Intent bookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amzn.to/3fcXoPW"));
            startActivity(bookIntent);
        });

        Logout.setOnClickListener(view -> {
            stopService(musicIntent);
            Intent returnIntent = new Intent(getApplicationContext(), GuestLoginActivity.class);
            startActivity(returnIntent);
            finish();
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