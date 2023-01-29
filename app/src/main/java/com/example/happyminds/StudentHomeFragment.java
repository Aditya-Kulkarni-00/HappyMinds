package com.example.happyminds;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Calendar;

public class StudentHomeFragment extends Fragment {

    String username;
    TextView user, greeting;
    ImageView changeSong , Logout , book1, book2, book3 , book4 ;
    Button SoundCorner, BreathWork, Experts , Helpline;
    CarouselView view2;
    Intent musicIntent;
    Boolean backgroundSoundPlaying = false;
    int[] backgroundQuoteImages = {R.drawable.backgroundquotes1, R.drawable.backgroundquotes2,R.drawable.backgroundquotes3,  R.drawable.backgroundquotes4,R.drawable.backgroundquotes5};

    StudentDetails details;
    public StudentHomeFragment() {
        // Required empty public constructor
    }

    public StudentHomeFragment(StudentDetails studentDetails){
        details = studentDetails;
    }

    public static StudentHomeFragment newInstance(String param1, String param2) {
        return new StudentHomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        musicIntent = new Intent(requireContext(), BackgroundMusicService.class);
        user=  (TextView) requireView().findViewById(R.id.GuestHomeScreenUsername);
        greeting = (TextView) requireView().findViewById(R.id.GuestHomeGreeting);

        Logout = requireView().findViewById(R.id.guestlogout);
        book1 = requireView().findViewById(R.id.book1);
        book2 = requireView().findViewById(R.id.book2);
        book3 = requireView().findViewById(R.id.book3);
        book4 = requireView().findViewById(R.id.book4);
        SoundCorner = requireView().findViewById(R.id.GuestHomeSoundCorner);
        BreathWork  = requireView().findViewById(R.id.GuestHomeBreathwork);
        Experts = requireView().findViewById(R.id.GuestHomeExperts);
        Helpline = requireView().findViewById(R.id.GuestHomeHelpline);
        changeSong = requireView().findViewById(R.id.changeBackground);

        view2 = (CarouselView) requireView().findViewById(R.id.BackgroundQuotes);
        user.setText(details.getName());
        greeting(greeting);


        SoundCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(requireContext(), SoundCornerActivity.class);
                startActivity(intent1);

            }
        });

        BreathWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(requireContext(), BreathworkActivity.class);
                startActivity(intent1);
            }
        });

        changeSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(backgroundSoundPlaying){
                    stopPlaying();
                }else {
                    startPlaying();
                }
            }
        });

        view2.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(backgroundQuoteImages[position]);
            }
        });


        view2.setPageCount(backgroundQuoteImages.length);
        book1.setOnClickListener(view3 -> {
            Intent bookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amzn.to/3fd8PHk"));
            startActivity(bookIntent);
        });

        book2.setOnClickListener(view3 -> {
            Intent bookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amzn.to/3TKeZ0L"));
            startActivity(bookIntent);
        });

        book3.setOnClickListener(view3 -> {
            Intent bookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amzn.to/3zrFShP"));
            startActivity(bookIntent);
        });

        book4.setOnClickListener(view3 -> {
            Intent bookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amzn.to/3fcXoPW"));
            startActivity(bookIntent);
        });

        Logout.setOnClickListener(view3 -> {
            requireContext().stopService(musicIntent);
            requireActivity().finish();
            System.exit(1);

        });

        Helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(requireContext(), HelplineActivity.class);
                startActivity(intent1);
            }
        });

        Experts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(requireContext() , ExpertActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onPause() {
        stopPlaying();
        super.onPause();
    }

    private void greeting(TextView greetingView ){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour > 0 && hour < 12){
            greetingView.setText("Good Morning");
        }else if(hour > 12 && hour < 17){
            greetingView.setText("Good Afternoon");
        }else if(hour > 17 && hour < 20){
            greetingView.setText("Good Evening");
        }else {
            greetingView.setText("Good Night");
        }


    }

    private void startPlaying(){
        musicIntent.putExtra("musicName", "waves");
        requireContext().startService(musicIntent);
        backgroundSoundPlaying = true;
    }

    private void stopPlaying(){
        requireContext().stopService(musicIntent);
        backgroundSoundPlaying = false;
    }
}