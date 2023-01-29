package com.example.happyminds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JournalMessages extends AppCompatActivity {
    ArrayList<String> messages;
    Button Calm, Confident , Excited , Grateful , Happy , Inspired , Loved , Proud , Motivated;
    Button Annoyed , Guilty , Nervous , Sad , Worried , Disappointed , Frustuated , Lonely , Stressed;
    ProgressDialog dialog;
    String mobile;
    Button continueBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_messages);
        messages = new ArrayList<>();
        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }


        mobile = getIntent().getStringExtra("mobile");

        dialog = new ProgressDialog(JournalMessages.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Fetching from database");
        dialog.setMessage("Please wait while we load the data");

        Calm = findViewById(R.id.JournalMessageActivityCalm);
        Calm.setOnClickListener(view -> {
            if(messages.contains("positive : Calm")){
                messages.remove("positive : Calm");
                Calm.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Calm");
                Calm.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Confident = findViewById(R.id.JournalMessageActivityConfident);
        Confident.setOnClickListener(view -> {
            if(messages.contains("positive : Confident")){
                messages.remove("positive : Confident");
                Confident.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Confident");
                Confident.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Excited = findViewById(R.id.JournalMessageActivityExcited);
        Excited.setOnClickListener(view -> {
            if(messages.contains("positive : Excited")){
                messages.remove("positive : Excited");
                Excited.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Excited");
                Excited.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Grateful = findViewById(R.id.JournalMessageActivityGrateful);
        Grateful.setOnClickListener(view -> {
            if(messages.contains("positive : Grateful")){
                messages.remove("positive : Grateful");
                Grateful.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Grateful");
                Grateful.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Happy = findViewById(R.id.JournalMessageActivityHappy);
        Happy.setOnClickListener(view -> {
            if(messages.contains("positive : Happy")){
                messages.remove("positive : Happy");
                Happy.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Happy");
                Happy.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Inspired = findViewById(R.id.JournalMessageActivityInspired);
        Inspired.setOnClickListener(view -> {
            if(messages.contains("positive : Inspired")){
                messages.remove("positive : Inspired");
                Inspired.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Inspired");
                Inspired.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Loved = findViewById(R.id.JournalMessageActivityLoved);
        Loved.setOnClickListener(view -> {
            if(messages.contains("positive : Loved")){
                messages.remove("positive : Loved");
                Loved.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Loved");
                Loved.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Proud = findViewById(R.id.JournalMessageActivityProud);
        Proud.setOnClickListener(view -> {
            if(messages.contains("positive : Proud")){
                messages.remove("positive : Proud");
                Proud.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Proud");
                Proud.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Motivated = findViewById(R.id.JournalMessageActivityMotivated);
        Motivated.setOnClickListener(view -> {
            if(messages.contains("positive : Motivated")){
                messages.remove("positive : Motivated");
                Motivated.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("positive : Motivated");
                Motivated.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Annoyed = findViewById(R.id.JournalMessageActivityAnnoyed);
        Annoyed.setOnClickListener(view -> {
            if(messages.contains("negative : Annoyed")){
                messages.remove("negative : Annoyed");
                Annoyed.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Annoyed");
                Annoyed.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Disappointed = findViewById(R.id.JournalMessageActivityDisappointed);
        Disappointed.setOnClickListener(view -> {
            if(messages.contains("negative : Disappointed")){
                messages.remove("negative : Disappointed");
                Disappointed.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Disappointed");
                Disappointed.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Frustuated = findViewById(R.id.JournalMessageActivityFrustuated);
        Frustuated.setOnClickListener(view -> {
            if(messages.contains("negative : Frustuated")){
                messages.remove("negative : Frustuated");
                Frustuated.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Frustuated");
                Frustuated.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Guilty = findViewById(R.id.JournalMessageActivityGuilty);
        Guilty.setOnClickListener(view -> {
            if(messages.contains("negative : Guilty")){
                messages.remove("negative : Guilty");
                Guilty.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Guilty");
                Guilty.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Worried = findViewById(R.id.JournalMessageActivityWorried);
        Worried.setOnClickListener(view -> {
            if(messages.contains("negative : Worried")){
                messages.remove("negative : Worried");
                Worried.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Worried");
                Worried.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Lonely = findViewById(R.id.JournalMessageActivityLonely);
        Lonely.setOnClickListener(view -> {
            if(messages.contains("negative : Lonely")){
                messages.remove("negative : Lonely");
                Lonely.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Lonely");
                Lonely.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });


        Nervous = findViewById(R.id.JournalMessageActivityNervous);
        Nervous.setOnClickListener(view -> {
            if(messages.contains("negative : Nervous")){
                messages.remove("negative : Nervous");
                Nervous.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Nervous");
                Nervous.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Sad = findViewById(R.id.JournalMessageActivitySad);
        Sad.setOnClickListener(view -> {
            if(messages.contains("negative : Sad")){
                messages.remove("negative : Sad");
                Sad.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Sad");
                Sad.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        Stressed = findViewById(R.id.JournalMessageActivityStressed);
        Stressed.setOnClickListener(view -> {
            if(messages.contains("negative : Stressed")){
                messages.remove("negative : Stressed");
                Stressed.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }else{
                messages.add("negative : Stressed");
                Stressed.setBackgroundColor(getResources().getColor(R.color.teal_700));
            }
        });

        continueBtn = findViewById(R.id.JournalMessageActivityContinue);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(messages.isEmpty()){
                    Toast.makeText(JournalMessages.this, "Select A Emotion!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    dialog.show();
                    setStudent(mobile , messages);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Complete the Journal Thank you!", Toast.LENGTH_SHORT).show();
    }



    private void setStudent(String mobile, ArrayList<String> message) {
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
                        data.setEmotions(message);

                        DatabaseReference reference1 = database.getReference("colleges").child(details.getCollegeUID());
                        reference1.setValue(collegeDetails, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if(dialog.isShowing()){
                                    dialog.dismiss();
                                }
                                Intent intent = new Intent(getApplicationContext() , JournalMessagesContinued.class);
                                intent.putExtra("mobile" , mobile);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(JournalMessages.this, "Error in DB |!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}