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
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class StudentTestFragment extends Fragment {
    StudentDetails details1;
    public StudentTestFragment() {
        // Required empty public constructor
    }

    public StudentTestFragment(StudentDetails details){
        this.details1 = details;
    }
    public static StudentTestFragment newInstance() {
        return new StudentTestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView daysRemaining = requireActivity().findViewById(R.id.TimeRemaining);
        Button giveSecondTest = requireActivity().findViewById(R.id.StudentTestSecondTest);

        String date = details1.getTestDate();
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime testDate = LocalDateTime.parse(date , formatter);
            LocalDateTime currentDate = LocalDateTime.now();

//            LocalDateTime expectedDate = testDate.plusDays(21);
//            long days = currentDate.until(expectedDate , ChronoUnit.DAYS);
//            long hours = currentDate.until(expectedDate , ChronoUnit.HOURS)%60;
//            String builderstr = "Time Remaining " + days + "Days " + hours + " hours";


            LocalDateTime expectedDate = testDate.plusMinutes((3));

            long days = currentDate.until(expectedDate , ChronoUnit.MINUTES);
            long hours = currentDate.until(expectedDate , ChronoUnit.SECONDS)%60;
            if(days > 0){
                String builderstr = "Time Remaining " + days + "Minutes " + hours + " Seconds";
                daysRemaining.setText(builderstr);
                giveSecondTest.setVisibility(View.GONE);
            }else {
                if(!details1.getSecondTakenTest()){
                    String builderstr = "Click on below button to give second test";
                    daysRemaining.setText(builderstr);
                    giveSecondTest.setVisibility(View.VISIBLE);
                }else {
                    String builderstr = "Test Already Completed !!!";
                    daysRemaining.setText(builderstr);
                    giveSecondTest.setVisibility(View.GONE);
                }
                giveSecondTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(requireContext() , SecondTestActivity.class);
                        intent.putExtra("mobile" , details1.getMobile());
                        startActivity(intent);
                    }
                });
            }
        }


    }
}
