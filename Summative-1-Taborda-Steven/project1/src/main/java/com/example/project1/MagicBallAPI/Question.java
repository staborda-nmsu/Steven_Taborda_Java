package com.example.project1.MagicBallAPI;

public class Question {
    private int id;
    private String question;

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }
}
