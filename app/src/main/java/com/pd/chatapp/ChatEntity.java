package com.pd.chatapp;

public class ChatEntity {
    String message, user, time;

    public ChatEntity() {
    }

    public ChatEntity(String message, String user, String time) {
        this.message = message;
        this.user = user;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
