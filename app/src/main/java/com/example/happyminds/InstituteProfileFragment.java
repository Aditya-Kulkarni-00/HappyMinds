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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class InstituteProfileFragment extends Fragment {
    Button signOut , deleteUser, editProfile, feedback , faq , aboutUs;

    public InstituteProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institute_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signOut = (Button) requireView().findViewById(R.id.InstituteProfileSignOut);
        deleteUser = (Button) requireView().findViewById(R.id.InstituteProfileDeleteAccount);
        editProfile = (Button) requireView().findViewById(R.id.InstituteProfileEditProfile);
        feedback = (Button) requireView().findViewById(R.id.InstituteProfileFeedback);
        faq = (Button) requireView().findViewById(R.id.InstituteProfileFAQ);
        aboutUs = (Button) requireView().findViewById(R.id.InstituteProfileAboutUs);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstituteSignInActivity.googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(requireActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstituteSignInActivity.googleSignInClient.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(requireActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}