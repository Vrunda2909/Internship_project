package com.example.sqllearn;

public class QueryQuestionModel {
    private String question,option,correctAnswer;
//    public static  String[]  que = {"first","second","third"};
//    public static  String[]  ans = {"f","s","t"};

    public QueryQuestionModel(String question, String option, String correctAnswer) {
        this.question = question;
        this.option = option;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String optionA) {
        this.option = option;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
