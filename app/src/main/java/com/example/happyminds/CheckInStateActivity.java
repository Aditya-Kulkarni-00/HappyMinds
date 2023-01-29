package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CheckInStateActivity extends AppCompatActivity {
    ImageView veryhappy , happy , neutral , sad , verysad;
    int score = 0;

    String mobile;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_state);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        mobile = getIntent().getStringExtra("mobile");

        veryhappy = findViewById(R.id.CheckInStateVeryHappy);
        happy = findViewById(R.id.CheckInStateHappy);
        neutral = findViewById(R.id.CheckInStateNeutral);
        sad = findViewById(R.id.CheckInStateSad);
        verysad = findViewById(R.id.CheckInStateVerySad);

        veryhappy.setOnClickListener(view -> score = 5);
        happy.setOnClickListener(view -> score = 4);
        neutral.setOnClickListener(view -> score = 3);
        sad.setOnClickListener(view -> score = 2);
        verysad.setOnClickListener(view -> score = 1);

        dialog = new ProgressDialog(CheckInStateActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Fetching from database");
        dialog.setMessage("Please wait while we load the data");


        Button continueBtn = findViewById(R.id.CheckInStateContinue);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialog !=null){
                    dialog.show();
                }

                searchForStudent(mobile);

            }
        });

    }


    private void searchForStudent(String mob){
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
                    StudentDetails details = null;
                    if (collegeDetails != null) {
                        details = collegeDetails.getStudentByMobile(mobile, getApplicationContext());
                    }
                    if(details != null){
                        foundInDatabase = true;
                        DailyCheckIn checkIn = new DailyCheckIn();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            checkIn.setLastDate(checkIn.formattedDate(LocalDateTime.now()));
                            checkIn.setScore(score);
                        }
                        details.getCheckInData().add(checkIn);
                        DatabaseReference reference1 = database.getReference("colleges").child(details.getCollegeUID());
                        reference1.setValue(collegeDetails, (error, ref) -> {
                            dialog.dismiss();
                            Toast.makeText(CheckInStateActivity.this, "CheckIn Complete", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext() , JournalMessages.class);
                            intent.putExtra("mobile" , mobile);
                            startActivity(intent);
                            finish();
                        });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error Connecting to Database !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}