package com.example.project1.MagicBallAPI;

class Answer {
    private int id;
    private String question;
    private String answer;

    public Answer(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
