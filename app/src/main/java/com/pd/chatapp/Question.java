package com.pd.chatapp;

public class FAQ {
    String Question, Answer, Chapter;

    @Override
    public String toString() {
        return "FAQ{" +
                "Question='" + Question + '\'' +
                ", Answer='" + Answer + '\'' +
                ", Chapter='" + Chapter + '\'' +
                '}';
    }

    public FAQ(String question, String answer, String chapter) {
        Question = question;
        Answer = answer;
        Chapter = chapter;
    }

    public FAQ() {
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
}
