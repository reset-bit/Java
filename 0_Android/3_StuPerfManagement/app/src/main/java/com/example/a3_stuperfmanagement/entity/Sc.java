package com.example.a3_stuperfmanagement.entity;

public class Sc {
    private String cname;
    private int score;

    public Sc(String cname, int score) {
        this.cname = cname;
        this.score = score;
    }

    public String getCname() {
        return cname;
    }

    public int getScore() {
        return score;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
