package com.example.happyminds;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class StudentActivityFragment extends Fragment {
    StudentDetails details;
    public StudentActivityFragment() {
        // Required empty public constructor
    }

    public StudentActivityFragment(StudentDetails mdetails){
        details = mdetails;
    }

    public static StudentActivityFragment newInstance() {
        return new StudentActivityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button sessions = requireView().findViewById(R.id.StudentActivitySessions);
        Button journal = requireView().findViewById(R.id.StudentActivityJournal);
        Button twentyOneDays = requireView().findViewById(R.id.StudentActivity21DaysC);
        ImageView water , air , fire , akash , earth;

        water = requireView().findViewById(R.id.StudentActivityWater);
        air = requireView().findViewById(R.id.StudentActivityAir);

        fire = requireView().findViewById(R.id.StudentActivityFire);
        akash = requireView().findViewById(R.id.StudentActivityAkash);
        earth = requireView().findViewById(R.id.Earth);


        water.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext() , WaterActivity.class);
            startActivity(intent);
        });

        air.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext() , AirActivity.class);
            startActivity(intent);
        });

        fire.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext() , FireActivity.class);
            startActivity(intent);
        });

        akash.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext() , AkashActivity.class);
            startActivity(intent);
        });

        earth.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext() , StudentEarthActivity.class);
            startActivity(intent);
        });

        sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(details!=null){
                    if(details.getTotalScore() == 0){
                        details.calculateScore();

                    }
                    if(details.getTotalScore() > 42){
                        Intent intent = new Intent(requireContext(), ActivitySessionHard.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(requireContext(), ActivitySessionEasy.class);
                        startActivity(intent);
                    }
                }

            }
        });


        journal.setOnClickListener(view13 -> {
            Intent intent = new Intent(requireContext() , JournalStartActivity.class);
            intent.putExtra("mobile" , details.getMobile());
            startActivity(intent);
        });

        twentyOneDays.setOnClickListener(view12 -> {
            Intent intent = new Intent(requireContext() , TwentyOneDaysChallenge.class);
            intent.putExtra("mobile" , details.getMobile());
            intent.putExtra("college" , details.getCollegeUID());
            startActivity(intent);
        });
    }
}