package com.example.happyminds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class InstituteHomeFragment extends Fragment {
    TextView CollegeName, CollegeAddress, CollegeMobile;
    FirebaseDatabase database;
    DatabaseReference reference;
    GoogleSignInAccount account;
    public InstituteHomeFragment() {
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

        return inflater.inflate(R.layout.fragment_institute_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CollegeName = (TextView) requireView().findViewById(R.id.InstituteHomeFragmentCollegeName);
        CollegeAddress = (TextView) requireView().findViewById(R.id.InstituteHomeFragmentCollegeAddress);
        CollegeMobile = (TextView) requireView().findViewById(R.id.InstituteHomeFragmentCollegeMobile);
        updateUI(CollegeName, CollegeAddress, CollegeMobile);

    }

    private void updateUI(TextView name , TextView address, TextView mobile){
        database = FirebaseDatabase.getInstance();
        account = GoogleSignIn.getLastSignedInAccount(requireContext());
        reference = database.getReference("colleges").child(Objects.requireNonNull(account.getId()));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CollegeDetails details = (CollegeDetails) snapshot.getValue(CollegeDetails.class);
                if (details != null) {
                    name.setText(details.getName());
                    address.setText(details.getAddress());
                    mobile.setText(details.getPhone());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}