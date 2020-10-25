package com.example.firebaseex;

import com.firebase.ui.auth.data.model.User;

public class Game {
    String user1;
    String user2;
    String data;

    public Game() {
    }

    public Game(String user1, String user2, String data) {
        this.user1 = user1;
        this.user2 = user2;
        this.data = data;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
