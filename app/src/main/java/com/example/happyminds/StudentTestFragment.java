package com.example.happyminds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        String date = details1.getTestDate();
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime testDate = LocalDateTime.parse(date , formatter);
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime expectedDate = testDate.plusDays(21);

            long days = currentDate.until(expectedDate , ChronoUnit.DAYS);
            long hours = currentDate.until(expectedDate , ChronoUnit.HOURS)%60;
            String builderstr = "Time Remaining " + days + "Days " + hours + " hours";
            daysRemaining.setText(builderstr);
        }


    }
}
