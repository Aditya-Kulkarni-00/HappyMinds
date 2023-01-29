package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JournalMessagesContinued extends AppCompatActivity {
    Button Family , Friends , Love , Work , School , Sleep , Food , Exercise , Screens , Travels , Games , Shopping;
    EditText secretMessages;
    ArrayList<String> activity;
    Button submit;
    String mobile;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_messages_continued);
        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }


        mobile = getIntent().getStringExtra("mobile");

        dialog = new ProgressDialog(JournalMessagesContinued.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Fetching from database");
        dialog.setMessage("Please wait while we load the data");

        activity = new ArrayList<>();

        secretMessages = findViewById(R.id.JournalMessageActivitySecretMessage);

        Family = findViewById(R.id.JournalMessageActivityFamily);
        Family.setOnClickListener(view -> {
            if(activity.contains("Family")){
                activity.remove("Family");
                Family.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Family");
                Family.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Friends = findViewById(R.id.JournalMessageActivityFriends);
        Friends.setOnClickListener(view -> {
            if(activity.contains("Friends")){
                activity.remove("Friends");
                Friends.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Friends");
                Friends.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Love = findViewById(R.id.JournalMessageActivityLove);
        Love.setOnClickListener(view -> {
            if(activity.contains("Love")){
                activity.remove("Love");
                Love.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Love");
                Love.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Work = findViewById(R.id.JournalMessageActivityWork);
        Work.setOnClickListener(view -> {
            if(activity.contains("Work")){
                activity.remove("Work");
                Work.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Work");
                Work.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        School = findViewById(R.id.JournalMessageActivitySchool);
        School.setOnClickListener(view -> {
            if(activity.contains("School")){
                activity.remove("School");
                School.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("School");
                School.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Sleep = findViewById(R.id.JournalMessageActivitySleep);
        Sleep.setOnClickListener(view -> {
            if(activity.contains("Sleep")){
                activity.remove("Sleep");
                Sleep.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Sleep");
                Sleep.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Food = findViewById(R.id.JournalMessageActivityFood);
        Food.setOnClickListener(view -> {
            if(activity.contains("Food")){
                activity.remove("Food");
                Food.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Food");
                Food.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Exercise = findViewById(R.id.JournalMessageActivityExercise);
        Exercise.setOnClickListener(view -> {
            if(activity.contains("Exercise")){
                activity.remove("Exercise");
                Exercise.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Exercise");
                Exercise.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Screens = findViewById(R.id.JournalMessageActivityScreens);
        Screens.setOnClickListener(view -> {
            if(activity.contains("Screens")){
                activity.remove("Screens");
                Screens.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Screens");
                Screens.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });


        Travels = findViewById(R.id.JournalMessageActivityTravel);
        Travels.setOnClickListener(view -> {
            if(activity.contains("Travels")){
                activity.remove("Travels");
                Travels.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Travels");
                Travels.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Games = findViewById(R.id.JournalMessageActivityGames);
        Games.setOnClickListener(view -> {
            if(activity.contains("Games")){
                activity.remove("Games");
                Games.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Games");
                Games.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Shopping = findViewById(R.id.JournalMessageActivityShopping);
        Shopping.setOnClickListener(view -> {
            if(activity.contains("Shopping")){
                activity.remove("Shopping");
                Shopping.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                activity.add("Shopping");
                Shopping.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });


        submit = findViewById(R.id.JournalMessageActivitySubmit);
        submit.setOnClickListener(view -> {
            String mesg = secretMessages.getText().toString();
            if(mesg.equals("")){
                Toast.makeText(JournalMessagesContinued.this, "Enter Message | Thank you", Toast.LENGTH_SHORT).show();
            }else {
                if(activity.isEmpty()){
                    Toast.makeText(JournalMessagesContinued.this, "Select an Activity !", Toast.LENGTH_SHORT).show();
                }else {
                    dialog.show();
                    setStudent(mobile  , activity , mesg);
                }
            }
        });
    }

    private void setStudent(String mobile, ArrayList<String> activities , String secretMesg) {
        FirebaseDatabase database;
        DatabaseReference reference;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("colleges");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean foundInDatabase = false;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    CollegeDetails collegeDetails = snapshot1.getValue(CollegeDetails.class);
                    StudentDetails details = null;
                    if (collegeDetails != null) {
                        details = collegeDetails.getStudentByMobile(mobile, getApplicationContext());
                    }
                    if (details != null) {
                        foundInDatabase = true;
                        DailyCheckIn data = details.getCheckInData().get(details.getCheckInData().size() - 1);
                        data.setMessage(secretMesg);
                        data.setActivities(activities);
                        DatabaseReference reference1 = database.getReference("colleges").child(details.getCollegeUID());
                        reference1.setValue(collegeDetails, (error, ref) -> {
                            if(dialog.isShowing()){
                                dialog.dismiss();
                            }
                            Toast.makeText(JournalMessagesContinued.this, "Thank you for Feedback", Toast.LENGTH_LONG).show();
                            Intent intent =  new Intent(getApplicationContext() , StudentDashboardActivity.class);
                            intent.putExtra("mobile" , mobile);
                            JournalMessagesContinued.this.finishAffinity();
                            startActivity(intent);

                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(JournalMessagesContinued.this, "Error in DB |!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Complete the Journal Thank you!", Toast.LENGTH_SHORT).show();
    }
}