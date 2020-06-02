package com.pd.chatapp;

public class Question {
    String Question, Answer, Chapter, Subject, askedTo, askedBy, time, status, isAccepted;

    public Question() {
    }

    public Question(String question, String answer, String chapter, String subject, String askedTo, String askedBy, String time, String status, String isAccepted) {
        Question = question;
        Answer = answer;
        Chapter = chapter;
        Subject = subject;
        this.askedTo = askedTo;
        this.askedBy = askedBy;
        this.time = time;
        this.status = status;
        this.isAccepted = isAccepted;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getChapter() {
        return Chapter;
    }

    public void setChapter(String chapter) {
        Chapter = chapter;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getAskedTo() {
        return askedTo;
    }

    public void setAskedTo(String askedTo) {
        this.askedTo = askedTo;
    }

    public String getAskedBy() {
        return askedBy;
    }

    public void setAskedBy(String askedBy) {
        this.askedBy = askedBy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }
}
