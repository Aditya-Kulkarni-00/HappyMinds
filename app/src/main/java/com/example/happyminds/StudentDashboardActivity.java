package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDashboardActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Intent studentMobile;
    StudentDetails details = null;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        bottomNavigationView = findViewById(R.id.StudentDashboardNavBar);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Fetching from database");
        dialog.setMessage("Please wait while we load the data");
        dialog.show();
        studentMobile = getIntent();
        String mobileNumber = studentMobile.getStringExtra("mobile");
        searchForStudent(mobileNumber);

    }


    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.StudentDashboardRelativeLayout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(1);
    }


    private void searchForStudent(String  m){
        FirebaseDatabase database;
        DatabaseReference reference;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("colleges");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean foundInDatabase = false;
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    CollegeDetails collegeDetails = snapshot1.getValue(CollegeDetails.class);
                    if (collegeDetails != null) {
                        details = collegeDetails.getStudentByMobile(m, getApplicationContext());
                    }
                    if(details != null){
                        foundInDatabase = true;
                        if(dialog.isShowing()){
                            dialog.dismiss();
                        }
                        loadFragment(new StudentHomeFragment(details));
                        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                            Fragment fragment = null;
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                                switch (item.getItemId()){
                                    case R.id.studentNavHome: fragment = new StudentHomeFragment(details); break;
                                    case R.id.studentNavCommunity: fragment = new StudentCommunityFragment();break;
                                    case R.id.studentNavActivity: fragment = new StudentActivityFragment(details); break;
                                    case R.id.studentNavProfile: fragment = new StudentProfileFragment(details);break;
                                    case R.id.studentNavTest: fragment = new StudentTestFragment(details);break;
                                }

                                if(fragment !=null){
                                    loadFragment(fragment);
                                }
                                return true;
                            }
                        });
                    }
                }

                if(!foundInDatabase){
                    Toast.makeText(getApplicationContext(), "Please register your number in college", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error Connecting to Database !", Toast.LENGTH_SHORT).show();
            }
        });


    }

}