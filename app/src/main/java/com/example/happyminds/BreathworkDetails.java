package com.example.happyminds;

public class BreathworkDetails {
    String name;
    String directions,benefit1, benefit2, benefit3;
    int [] pattern;

    public BreathworkDetails(String name, String directions, String benefit1, String benefit2, String benefit3, int[] pattern) {
        this.name = name;
        this.directions = directions;
        this.benefit1 = benefit1;
        this.benefit2 = benefit2;
        this.benefit3 = benefit3;
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getBenefit1() {
        return benefit1;
    }

    public void setBenefit1(String benefit1) {
        this.benefit1 = benefit1;
    }

    public String getBenefit2() {
        return benefit2;
    }

    public void setBenefit2(String benefit2) {
        this.benefit2 = benefit2;
    }

    public String getBenefit3() {
        return benefit3;
    }

    public void setBenefit3(String benefit3) {
        this.benefit3 = benefit3;
    }

    public int[] getPattern() {
        return pattern;
    }

    public void setPattern(int[] pattern) {
        this.pattern = pattern;
    }
}
