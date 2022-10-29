package com.example.happyminds;

import java.util.ArrayList;
import java.util.Objects;

public class CollegeDetails {
    String name, email, address, pincode, phone , telephone, city, state;
    ArrayList<StudentDetails> students;
    public CollegeDetails(){

    }

    public StudentDetails getStudentByMobile(String mobile){
        for (int i = 0; i < students.size(); i++) {
            if(Objects.equals(students.get(i).getMobile(), mobile)){
                return students.get(i);
            }
        }
        return  null;
    }

    public void addStudent(String mobile){
        students.add(new StudentDetails(mobile));
    }

    public CollegeDetails(String name, String email, String address, String pincode, String phone, String telephone, String city, String state) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.pincode = pincode;
        this.phone = phone;
        this.telephone = telephone;
        this.city = city;
        this.state = state;
        students = new ArrayList<StudentDetails>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPincode() {
        return pincode;
    }

    public String getPhone() {
        return phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<StudentDetails> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<StudentDetails> students) {
        this.students = students;
    }
}
