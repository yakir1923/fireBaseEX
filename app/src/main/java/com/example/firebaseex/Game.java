package com.example.firebaseex;

import com.firebase.ui.auth.data.model.User;

public class Game {
    String user1;
    String user2;
    String data;
    boolean user1turn;
    int opponentPoints;
    String user1Name;
    String user2Name;

    public Game() {
    }

    public Game(String user1, String user2, String data) {
        this.user1 = user1;
        this.user2 = user2;
        this.data = data;
        user1turn=true;
        opponentPoints=0;
        //this.user1Name=user1Name;
        this.user2Name=" ";
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

    public boolean getUser1turn() {
        return user1turn;
    }

    public void setUser1turn(boolean user1turn) {
        this.user1turn = user1turn;
    }

    public int getOpponentPoints() {
        return opponentPoints;
    }

    public void setOpponentPoints(int opponentPoints) {
        this.opponentPoints = opponentPoints;
    }

    public String getUser1Name() {
        return user1Name;
    }

    public void setUser1Name(String user1Name) {
        this.user1Name = user1Name;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }
}

