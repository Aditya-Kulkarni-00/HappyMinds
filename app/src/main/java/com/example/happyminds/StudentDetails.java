package com.example.happyminds;

public class StudentDetails {
    String mobile, rollno, age , name;

    String password;


    public StudentDetails(){

    }

    public StudentDetails(String mobile, String rollno, String age, String name) {
        this.mobile = mobile;
        this.rollno = rollno;
        this.age = age;
        this.name = name;
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

}