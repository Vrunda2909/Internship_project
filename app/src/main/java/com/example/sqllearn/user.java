package com.example.sqllearn;

public class user {
    private String Name;
    private String Email;
    private int quizvalue = 0,queryvalue = 0,finalscore = 0;
    private int quizone = 0,quiztwo= 0,quizthree= 0,quizfour= 0,quizfive= 0,queryone= 0,querytwo= 0,querythree= 0,queryfour= 0,queryfive= 0;


    public user() {
    }


    public user(String name, String email, int quizvalue, int queryvalue, int finalscore, int quizone, int quiztwo, int quizthree, int quizfour, int quizfive, int queryone, int querytwo, int querythree, int queryfour, int queryfive) {
        Name = name;
        Email = email;
        this.quizvalue = quizvalue;
        this.queryvalue = queryvalue;
        this.finalscore = finalscore;
        this.quizone = quizone;
        this.quiztwo = quiztwo;
        this.quizthree = quizthree;
        this.quizfour = quizfour;
        this.quizfive = quizfive;
        this.queryone = queryone;
        this.querytwo = querytwo;
        this.querythree = querythree;
        this.queryfour = queryfour;
        this.queryfive = queryfive;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public int getQuizvalue() {
        return quizvalue;
    }

    public void setQuizvalue(int quizvalue) {
        this.quizvalue = quizvalue;
    }

    public int getQueryvalue() {
        return queryvalue;
    }

    public void setQueryvalue(int queryvalue) {
        this.queryvalue = queryvalue;
    }

    public int getFinalscore() {
        return finalscore;
    }

    public void setFinalscore(int finalscore) {
        this.finalscore = finalscore;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getQuizone() {
        return quizone;
    }

    public void setQuizone(int quizone) {
        this.quizone = quizone;
    }

    public int getQuiztwo() {
        return quiztwo;
    }

    public void setQuiztwo(int quiztwo) {
        this.quiztwo = quiztwo;
    }

    public int getQuizthree() {
        return quizthree;
    }

    public void setQuizthree(int quizthree) {
        this.quizthree = quizthree;
    }

    public int getQuizfour() {
        return quizfour;
    }

    public void setQuizfour(int quizfour) {
        this.quizfour = quizfour;
    }

    public int getQuizfive() {
        return quizfive;
    }

    public void setQuizfive(int quizfive) {
        this.quizfive = quizfive;
    }

    public int getQueryone() {
        return queryone;
    }

    public void setQueryone(int queryone) {
        this.queryone = queryone;
    }

    public int getQuerytwo() {
        return querytwo;
    }

    public void setQuerytwo(int querytwo) {
        this.querytwo = querytwo;
    }

    public int getQuerythree() {
        return querythree;
    }

    public void setQuerythree(int querythree) {
        this.querythree = querythree;
    }

    public int getQueryfour() {
        return queryfour;
    }

    public void setQueryfour(int queryfour) {
        this.queryfour = queryfour;
    }

    public int getQueryfive() {
        return queryfive;
    }

    public void setQueryfive(int queryfive) {
        this.queryfive = queryfive;
    }
}
