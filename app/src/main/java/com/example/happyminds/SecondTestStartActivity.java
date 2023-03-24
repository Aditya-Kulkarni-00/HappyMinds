package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SecondTestStartActivity extends AppCompatActivity {
    String mobile;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<QuestionFormat> ques;
    TextView currentQTitle , currentQNumber;
    Button opt1 , opt2 , opt3 , opt4;
    Integer currentA = 0;
    Button event;
    ProgressDialog dialog;
    ArrayList<Integer> localTestScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_test_start);


        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        mobile = getIntent().getStringExtra("mobile");

        currentQTitle = findViewById(R.id.StudentTestQTitle);
        currentQNumber = findViewById(R.id.StudentTestQId);

        opt1 = findViewById(R.id.StudentTestQOption1);
        opt2 = findViewById(R.id.StudentTestQOption2);
        opt3 = findViewById(R.id.StudentTestQOption3);
        opt4 = findViewById(R.id.StudentTestQOption4);
        localTestScores = new ArrayList<>();
        event = findViewById(R.id.StudentTestQEvent);
        dialog = new ProgressDialog(SecondTestStartActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Fetching from database");
        dialog.setMessage("Please wait while we load the data");

        fetchDetails(); // iget ques in aarraylist;
    }

    private void handleUI(int quesId , StudentDetails details) {
//            fetch from list;


        QuestionFormat q = ques.get(quesId);
//            update UI
        currentQNumber.setText(String.valueOf(quesId+1));
        currentQTitle.setText(q.getTitle());

        opt1.setText(q.getOption1());
        opt2.setText(q.getOption2());
        opt3.setText(q.getOption3());
        opt4.setText(q.getOption4());
//            setOnClick
        opt1.setOnClickListener(view -> {

            currentA = 1;
            opt1.setBackgroundColor(getResources().getColor(R.color.teal_700));
            opt2.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt3.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt4.setBackgroundColor(getResources().getColor(R.color.purple_500));

        });

        opt2.setOnClickListener(view -> {

            currentA = 2;
            opt1.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt2.setBackgroundColor(getResources().getColor(R.color.teal_700));
            opt3.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt4.setBackgroundColor(getResources().getColor(R.color.purple_500));

        });

        opt3.setOnClickListener(view -> {

            currentA = 3;
            opt1.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt2.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt3.setBackgroundColor(getResources().getColor(R.color.teal_700));
            opt4.setBackgroundColor(getResources().getColor(R.color.purple_500));

        });

        opt4.setOnClickListener(view -> {

            currentA = 4;
            opt1.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt2.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt3.setBackgroundColor(getResources().getColor(R.color.purple_500));
            opt4.setBackgroundColor(getResources().getColor(R.color.teal_700));

        });
//            update event;

        if(quesId > 19){
            event.setText("Submit Test");
            event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    localTestScores.add(currentA);
                    currentA =0;
                    dialog.show();
                    reference.child(details.getCollegeUID()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            CollegeDetails collegeDetails = snapshot.getValue(CollegeDetails.class);
                            StudentDetails details1 = null;
                            if (collegeDetails != null) {
                                details1 = collegeDetails.getStudentByMobile(details.getMobile() , getApplicationContext());
                            }
                            if (details1 != null) {
                                details1.setReTestScores(localTestScores);
                                details1.calculateReScore();
                                details1.setSecondTakenTest(true);
                            }

                            DatabaseReference reference1 = database.getReference("colleges");
                            reference1.child(details.getCollegeUID()).setValue(collegeDetails, (error, ref) -> {
                                Toast.makeText(SecondTestStartActivity.this, "Successfully Gave the Test", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext() , StudentDashboardActivity.class);
                                intent.putExtra("mobile" , details.getMobile());
                                dialog.dismiss();
                                finish();
                                startActivity(intent);

                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            });
        }else {
            event.setText("Continue");
            event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(currentA == 0){
                        Toast.makeText(SecondTestStartActivity.this, "Select a Score", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    localTestScores.add(currentA);
                    currentA = 0;
                    opt1.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    opt2.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    opt3.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    opt4.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    if(quesId < 21){
                        handleUI(quesId+1 , details);
                    }
                }
            });

        }
    }

    private void fetchDetails() {

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
                        ques = details.getQuestions();
                        handleUI(0 , details);
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