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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TwentyOneDaysChallenge extends AppCompatActivity implements View.OnClickListener{
    Button One , Two , Three , Four , Five , Six , Seven;
    Button Eight , Nine , Ten , Eleven , Twelve , Thirteen , Fourteen;
    Button Fifteen , Sixteen , Seventeen , Eighteen , Nineteen , Twenty , TwentyOne;
    StudentDetails studentDetails;
    ProgressDialog dialog;
    Button [] buttons;
    String mobile , collegeUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_one_days_challenge);
        mobile = getIntent().getStringExtra("mobile");
        collegeUID = getIntent().getStringExtra("college");

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        dialog = new ProgressDialog(TwentyOneDaysChallenge.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Fetching from database");
        dialog.setMessage("Please wait while we load the data");
        dialog.show();
        One = findViewById(R.id.ChallengeOne);


        Two = findViewById(R.id.ChallengeTwo);
        Two.setOnClickListener(this);

        Three = findViewById(R.id.ChallengeThree);
        Three.setOnClickListener(this);

        Four = findViewById(R.id.ChallengeFour);
        Four.setOnClickListener(this);

        Five = findViewById(R.id.ChallengeFive);
        Five.setOnClickListener(this);

        Six = findViewById(R.id.ChallengeSix);
        Six.setOnClickListener(this);

        Seven = findViewById(R.id.ChallengeSeven);
        Seven.setOnClickListener(this);

        Eight = findViewById(R.id.ChallengeEight);
        Eight.setOnClickListener(this);

        Nine = findViewById(R.id.ChallengeNine);
        Nine.setOnClickListener(this);

        Ten = findViewById(R.id.ChallengeTen);
        Ten.setOnClickListener(this);

        Eleven = findViewById(R.id.ChallengeEleven);
        Eleven.setOnClickListener(this);

        Twelve = findViewById(R.id.ChallengeTwelve);
        Twelve.setOnClickListener(this);

        Thirteen = findViewById(R.id.ChallengeThirteen);
        Thirteen.setOnClickListener(this);

        Fourteen = findViewById(R.id.ChallengeFourteen);
        Fourteen.setOnClickListener(this);

        Fifteen = findViewById(R.id.ChallengeFifteen);
        Fifteen.setOnClickListener(this);

        Sixteen = findViewById(R.id.ChallengeSixteen);
        Sixteen.setOnClickListener(this);

        Seventeen = findViewById(R.id.ChallengeSeventeen);
        Seventeen.setOnClickListener(this);

        Eighteen = findViewById(R.id.ChallengeEighteen);
        Eighteen.setOnClickListener(this);

        Nineteen = findViewById(R.id.ChallengeNineteen);
        Nineteen.setOnClickListener(this);

        Twenty = findViewById(R.id.ChallengeTwenty);
        Twenty.setOnClickListener(this);

        TwentyOne = findViewById(R.id.ChallengeTwentyOne);
        TwentyOne.setOnClickListener(this);
        buttons = new Button[]{One, Two , Three , Four ,Five , Six , Seven , Eight , Nine , Ten , Eleven , Twelve , Thirteen , Fourteen , Fifteen , Sixteen , Seventeen , Eighteen , Nineteen , Twenty , TwentyOne};

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("colleges").child(collegeUID);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                CollegeDetails details = snapshot.getValue(CollegeDetails.class);
                if(details!=null){
                    studentDetails = details.getStudentByMobile(mobile , getApplicationContext());

                    if(studentDetails !=null){
                        int lastCompletedDay = studentDetails.getLastCompletedChallenge();

                        handleButtons(lastCompletedDay);
                        dialog.dismiss();
                        One.setOnClickListener(view -> {

                                Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    String date = studentDetails.getTestDate();
                                    if(date!=null){
                                        long hours = LocalDateTime.now().until(studentDetails.parsedDateTime(date) , ChronoUnit.DAYS);
                                        if(hours < 0){
                                            Toast.makeText(TwentyOneDaysChallenge.this, "Try Again Later!", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                                            intent.putExtra("challenge" , challenge.getChallenge());
                                            intent.putExtra("day" , challenge.getDay());
                                            startActivityForResult(intent , 1 );
                                        }
                                    }else {
                                        Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                                        intent.putExtra("challenge" , challenge.getChallenge());
                                        intent.putExtra("day" , challenge.getDay());
                                        startActivityForResult(intent , 1 );
                                    }


                                }



                        });

                        Two.setOnClickListener(view -> {

                            Challenges challenge = studentDetails.getTwentyOneDays().get(1);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                String date = studentDetails.getTestDate();
                                if(date!=null){
                                    long hours = LocalDateTime.now().until(studentDetails.parsedDateTime(date) , ChronoUnit.DAYS);
                                    if(hours < 1){
                                        Toast.makeText(TwentyOneDaysChallenge.this, "Try Again Later!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                                        intent.putExtra("challenge" , challenge.getChallenge());
                                        intent.putExtra("day" , challenge.getDay());
                                        startActivityForResult(intent , 2 );
                                    }
                                }else {
                                    Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                                    intent.putExtra("challenge" , challenge.getChallenge());
                                    intent.putExtra("day" , challenge.getDay());
                                    startActivityForResult(intent , 2 );
                                }


                            }
                        });


                        Three.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(2);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 3 );
                        });


                        Four.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 4 );
                        });


                        Five.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 5 );
                        });


                        Six.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 6 );
                        });

                        Seven.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 7 );
                        });


                        Eight.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 8 );
                        });


                        Nine.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 9 );
                        });

                        Ten.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 10 );
                        });


                        Eleven.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 11 );
                        });


                        Twelve.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 12 );
                        });

                        Thirteen.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 13 );
                        });


                        Fourteen.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 14 );
                        });


                        Fifteen.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 15 );
                        });


                        Sixteen.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 16 );
                        });

                        Seventeen.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 17 );
                        });


                        Eighteen.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 18 );
                        });


                        Nineteen.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 19 );
                        });


                        Twenty.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 20 );
                        });


                        TwentyOne.setOnClickListener(view -> {
                            Challenges challenge = studentDetails.getTwentyOneDays().get(0);
                            Intent intent = new Intent(getApplicationContext() , TwentyOneDaysChallengeToday.class);
                            intent.putExtra("challenge" , challenge.getChallenge());
                            intent.putExtra("day" , challenge.getDay());
                            startActivityForResult(intent , 21 );
                        });

                        handleButtons(lastCompletedDay);
                        }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    private void handleButtons(int id){
        for (int i = 0 ; i<id;i++){
            buttons[i].setBackgroundColor(getResources().getColor(R.color.teal_700));
            buttons[i].setClickable(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode >= 1 && requestCode <=21){
            dialog.show();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("colleges").child(collegeUID);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    CollegeDetails details = snapshot.getValue(CollegeDetails.class);
                    if (details != null) {

                        StudentDetails details1 = details.getStudentByMobile(mobile , getApplicationContext());
                        details1.getTwentyOneDays().get(requestCode - 1).setCompleted(true);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            details1.getTwentyOneDays().get(requestCode - 1).setChallengeDate(details1.formattedDate(LocalDateTime.now()));
                        }else {
                            Toast.makeText(TwentyOneDaysChallenge.this, "Android O is Required", Toast.LENGTH_SHORT).show();
                            System.exit(1);
                        }
                        reference.setValue(details, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                dialog.dismiss();
                                Toast.makeText(TwentyOneDaysChallenge.this, "Challenge Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            dialog.hide();
        }

    }
}