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

public class StudentProfileFragment extends Fragment {
    StudentDetails details;
    TextView sname;
    Button feedback , faq , aboutus , signout;
    public StudentProfileFragment() {
        // Required empty public constructor
    }

    public StudentProfileFragment(StudentDetails studentDetails){
        this.details = studentDetails;
    }
    public static StudentProfileFragment newInstance() {
        return new StudentProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sname = requireView().findViewById(R.id.StudentProfileName);
        sname.setText(details.getName());

        feedback = requireView().findViewById(R.id.StudentProfileFeedback);
        feedback.setOnClickListener(view1 -> {
            Intent i = new Intent(requireContext() , InstituteFeedbackActivity.class);
            startActivity(i);
        });
        faq = requireView().findViewById(R.id.StudentProfileFAQ);
        faq.setOnClickListener(view12 -> {
            Intent i = new Intent(requireContext() , InstituteFAQActivity.class);
            startActivity(i);
        });
        aboutus = requireView().findViewById(R.id.StudentProfileAboutUs);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext() , InstitutueAboutUs.class);
                startActivity(i);
            }
        });
        signout = requireView().findViewById(R.id.StudentProfileSignout);
        signout.setOnClickListener(view13 -> {
            requireActivity().finish();
            Intent i = new Intent(requireContext() , LoginActivity.class);
            startActivity(i);
        });



    }
}