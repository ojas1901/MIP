package com.pd.chatapp;

public class Doubt {
    String Chapter, Subject, Text;

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

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Doubt() {
    }

    public Doubt(String chapter, String subject, String text) {
        Chapter = chapter;
        Subject = subject;
        Text = text;
    }
}
