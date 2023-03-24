package com.example.happyminds;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class StudentDetails {
    public int getReTestScore() {
        return reTestScore;
    }

    public void setReTestScore(int reTestScore) {
        this.reTestScore = reTestScore;
    }

    public ArrayList<Integer> getReTestScores() {
        return reTestScores;
    }

    public void setReTestScores(ArrayList<Integer> reTestScores) {
        this.reTestScores = reTestScores;
    }

    String mobile, rollno, age , name;
    Boolean takenTest, secondTakenTest;
    String password;
    String CollegeUID;
    String testDate , lastDate;
    int totalScore , reTestScore;
    String reason , pincode , city , state;
    ArrayList<Challenges> twentyOneDays;
    ArrayList<DailyCheckIn> checkInData;
    Boolean hasSetPassword;
    ArrayList<Integer> testScores , reTestScores;
    ArrayList<QuestionFormat> questions;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public ArrayList<Challenges> getTwentyOneDays() {
        return twentyOneDays;
    }

    public void setTwentyOneDays(ArrayList<Challenges> twentyOneDays) {
        this.twentyOneDays = twentyOneDays;
    }



    public ArrayList<DailyCheckIn> getCheckInData() {
        return checkInData;
    }

    public void setCheckInData(ArrayList<DailyCheckIn> checkInData) {
        this.checkInData = checkInData;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void calculateScore(){
        for (Integer score : testScores){
            totalScore+=score;
        }
    }

    public void calculateReScore () {
        for (Integer score : reTestScores){
            reTestScore+=score;
        }
    }

    public Boolean getHasSetPassword() {
        return hasSetPassword;
    }

    public void setHasSetPassword(Boolean hasSetPassword) {
        this.hasSetPassword = hasSetPassword;
    }

    public String getCollegeUID() {
        return CollegeUID;
    }

    public void setCollegeUID(String collegeUID) {
        CollegeUID = collegeUID;
    }

    public ArrayList<Integer> getTestScores() {
        return testScores;
    }

    public void setTestScores(ArrayList<Integer> testScores) {
        this.testScores = testScores;
    }

    public ArrayList<QuestionFormat> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionFormat> questions) {
        this.questions = questions;
    }

    public StudentDetails(){
        takenTest = false;
        secondTakenTest = false;
        totalScore  = 0;
        reTestScore = 0;
        hasSetPassword = false;
        testScores = new ArrayList<>();
        reTestScores = new ArrayList<>();
        questions = new ArrayList<>();
        checkInData = new ArrayList<>();
        twentyOneDays = new ArrayList<>();
        addQuestions();
        addChallenges();
    }

    public Boolean getTakenTest() {
        return takenTest;
    }

    public void setTakenTest(Boolean takenTest) {
        this.takenTest = takenTest;
    }

    public StudentDetails(String mobile, String rollno, String age, String name) {
        totalScore  = 0;
        reTestScore = 0;
        this.mobile = mobile;
        this.rollno = rollno;
        this.age = age;
        this.name = name;
        takenTest = false;
        secondTakenTest = false;
        hasSetPassword = false;
        testScores = new ArrayList<>();
        reTestScores = new ArrayList<>();
        questions = new ArrayList<>();
        checkInData = new ArrayList<>();
        twentyOneDays = new ArrayList<>();
        addQuestions();
        addChallenges();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void addQuestions(){
        questions.add(new QuestionFormat("Sadness",
                "I do not feel sad",
                " I feel sad." ,
                " I am sad all the time and I can't snap out of it.",
                " I am so sad or unhappy that I can't stand it."));

        questions.add(new QuestionFormat("Pessimism",
                "I am not discouraged about my future.",
                " I feel more discouraged about my future than I used to be." ,
                "I do not expect things to work out for me.",
                "I feel my future is hopeless and will only get Worse."));
        questions.add(new QuestionFormat("Sense of Past Failure",
                "I do not feel like a failure.",
                "I feel I have failed more than the average person." ,
                "As I look back on my life, all I can see is a lot o failure",
                " I feel I am a complete failure as a person."));

        questions.add(new QuestionFormat("Loss of Pleasure",
                "I get as much pleasure as I ever did from the things I enjoy",
                "I do not enjoy things as much as I used to" ,
                "I get very little pleasure from the things I used to enjoy",
                "I can not enjoy any pleasure from the things I used to enjoy"));

        questions.add(new QuestionFormat("Guilt",
                "I do not feel particularly guilty",
                "I feel guilty over many things I have done or should have done" ,
                "I feel quite guilty most of the time",
                "I feel guilty all of the time."));

        questions.add(new QuestionFormat("Punishment feelings",
                "I do not feel I am being punished",
                "I feel I may be punished" ,
                "I expect to be punished",
                "I feel I am being punished"));

        questions.add(new QuestionFormat("Self-Dislikes",
                "I feel the same about myself as ever",
                "I have lost confidence in myself" ,
                "I am disappointed in myself",
                "I dislike myself"));

        questions.add(new QuestionFormat("Self-Criticalness",
                "I do not criticize or blame myself more than usual",
                "I am more critical of myself than I used to be",
                "I criticize myself for all of my faults",
                "I blame myself for everything bad that happens"
                ));

        questions.add(new QuestionFormat("Suicidal Thoughts",
                "I do not have any thoughts of killing myself",
                "I have thought of killing myself, but I would not carry them out" ,
                "I would like to kill myself",
                "I would kill myself if I had the chance"));


        questions.add(new QuestionFormat("Crying spells",
                "I do not cry anymore than I used to",
                "I cry more than I used to" ,
                "I cry over every little thing",
                "I feel like crying, but I can not"));

        questions.add(new QuestionFormat("Agitation",
                "I am no more restless or wound up than usual",
                " I feel sad." ,
                " I am sad all the time and I can't snap out of it.",
                " I am so sad or unhappy that I can't stand it."));

        questions.add(new QuestionFormat("Loss of Interest",
                "I have not lost in other people or activities",
                "I am less interested in other people or things than before",
                "I have lost of my interest in other people or things",
                "It is hard to get interested in anything"));

        questions.add(new QuestionFormat("Indecisiveness",
                "I make decisions about as well as ever",
                "I find it more difficult to make decision than usual",
                "I have much greater difficulty in making decision than I used to",
                "I have trouble making any decisions"));

        questions.add(new QuestionFormat("Worthlessness",
                "I do not feel I am worthless",
                "I do not consider myself as worthwhile and useful as I used to",
                "I feel more worthless as compared to other people",
                "I feel utterly worthless"
        ));

        questions.add(new QuestionFormat("Loss of Energy",
                "I have as much energy as ever",
                "I have less energy than I used to have",
                "I do not have enough energy to do very much",
                "I do not have enough energy to do anything"
        ));

        questions.add(new QuestionFormat("Change in sleep-pattern",
                "I have not experienced any change in my sleeping pattern",
                "I sleep a lot less than usual",
                "I sleep most of the day",
                "I wake up 1-2 hours early and can not get back to sleep"

        ));

        questions.add(new QuestionFormat("Irritability",
                "I am no more irritable than usual",
                "I am more irritable than usual",
                "I am much more irritable than usual",
                "I am irritable all the time"
                ));

        questions.add(new QuestionFormat("Changes in Appetite",
                "My appetite a lot more than usual",
                "My appetite a lot less than usual",
                "I have no appetite at all",
                "I crave food all the time"
                ));

        questions.add(new QuestionFormat("Concentration Difficulty",
                "I can concentrate as well as ever",
                "I can not concentrate as well as usual",
                "It is hard to keep my mind on anything for very long",
                "I find I can not concentrate on anything"));


        questions.add(new QuestionFormat("Fatigue",
                "I am no more tired or fatigued than usual",
                "I get more tired or fatigued more easily than usual" ,
                "I am too tired or fatigued to do a lot of the things I used to do",
                "I am too tired or fatigued to do most of the things I used to do"));


        questions.add(new QuestionFormat("Loss of interest in sex",
                "I have not noticed any recent change in my interesting sex",
                "I am less interested in sex than I used to" ,
                " I am much less interest in sex now",
                "I have lost interest in sex completely"));
    }

    private void addChallenges(){
        twentyOneDays.add(new Challenges("Take a walk outside" , 1));
        twentyOneDays.add(new Challenges("Drink a glass of water" , 2));
        twentyOneDays.add(new Challenges("Breathe in or diffuse lemon essential oil" , 3));
        twentyOneDays.add(new Challenges("Eat something healthy" , 4));
        twentyOneDays.add(new Challenges("Take a nap" , 5));
        twentyOneDays.add(new Challenges("Play a sport you love" , 6));
        twentyOneDays.add(new Challenges("If you stay indoors more, get out in the sun for at least 15 minutes to soak up some vitamin D." , 7));
        twentyOneDays.add(new Challenges("Do 10-15 squats in the shower" , 8));
        twentyOneDays.add(new Challenges("Drink more water." , 9));
        twentyOneDays.add(new Challenges("Go for a nature walk on the weekend" , 10));
        twentyOneDays.add(new Challenges("Get a massage" , 11));
        twentyOneDays.add(new Challenges("Have a healthy delicious meal with lots of greens" , 12));
        twentyOneDays.add(new Challenges("Drink smoothies." , 13));
        twentyOneDays.add(new Challenges("Have vitamin supplements if necessary." , 14));
        twentyOneDays.add(new Challenges("Put on a detoxifying face mask." , 15));
        twentyOneDays.add(new Challenges("Take a power nap." , 16));
        twentyOneDays.add(new Challenges("Make sure you include more fruits and veggies in your diet every week" , 17));
        twentyOneDays.add(new Challenges("Go hiking and camping." , 18));
        twentyOneDays.add(new Challenges("Do stretching exercises or yoga" , 19));
        twentyOneDays.add(new Challenges("Go for a swim. Swimming helps to relieve stress and relax" , 20));
        twentyOneDays.add(new Challenges("Take a warm bath. Turn off the lights and light candles around" , 21));
    }

    int getLastCompletedChallenge(){
        int day = 0;
        for(Challenges challenges : twentyOneDays){
            if(challenges.getCompleted()){
                day++;
            }else {
                return day;
            }
        }

        return 20;
    }

    String lastCompletedDate(){
        String date;
        for(Challenges challenges : twentyOneDays){
            if(!challenges.getCompleted()){
                int index = twentyOneDays.indexOf(challenges);
                if(index == 0){
                    return null;
                }else {
                    return twentyOneDays.get(index - 1).getChallengeDate();
                }
            }
        }

        return null;
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

    public Boolean getSecondTakenTest() {
        return secondTakenTest;
    }

    public void setSecondTakenTest(Boolean secondTakenTest) {
        this.secondTakenTest = secondTakenTest;
    }
}