package com.example.happyminds;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DailyCheckIn {
    int score;
    ArrayList<String> emotions;
    ArrayList<String> activities;
    String message;
    String lastDate;
    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public DailyCheckIn(int score, ArrayList<String> emotions, ArrayList<String> activities, String message, String lastDate) {
        this.score = score;
        this.emotions = emotions;
        this.activities = activities;
        this.message = message;
        this.lastDate = lastDate;
    }



    public DailyCheckIn() {
        emotions = new ArrayList<>();
        activities = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<String>  getEmotions() {
        return emotions;
    }

    public void setEmotions(ArrayList<String> emotions) {
        this.emotions = emotions;
    }
    public ArrayList<String>  getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String formattedDate (LocalDateTime time){
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }else {
            return null;
        }
        return time.format(formatter);
    }

    LocalDateTime parsedDateTime(String lastDate){
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(lastDate , formatter);

        }else{
            return null;
        }
    }
}
