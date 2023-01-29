package com.example.happyminds;

public class Challenges {
    String challenge;
    Boolean completed;
    String challengeDate;
    int Day;


    public Challenges(String challenge, int day) {
        this.challenge = challenge;
        Day = day;
        completed = false;
    }

    public Challenges() {
        completed = false;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getChallengeDate() {
        return challengeDate;
    }

    public void setChallengeDate(String challengeDate) {
        this.challengeDate = challengeDate;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }


}
