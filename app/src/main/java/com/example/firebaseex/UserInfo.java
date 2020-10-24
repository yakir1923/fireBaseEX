package com.example.firebaseex;

import java.util.ArrayList;

public class UserInfo {
    private  String name;
    private  String password;
    private  String email;
    private int level;
    private int userLose;
    private int userWin;
    private int playerPoints;

    private ArrayList<cards> spacedCards;

    public UserInfo() {
    }
//5
    public UserInfo(String name, String password, String email,int level,int userLose,int userWin) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.level=level;
        this.userLose=userLose;
        this.userWin=userWin;
        this.playerPoints=0;

    }
    public int getUserLose() {
        return userLose;
    }

    public void setUserLose(int userLose) {
        this.userLose = userLose;
    }

    public int getUserWin() {
        return userWin;
    }

    public void setUserWin(int userWin) {
        this.userWin = userWin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public ArrayList<cards> getSpacedCards() {
        return spacedCards;
    }

    public void setSpacedCards(ArrayList<cards> spacedCards) {
        this.spacedCards = spacedCards;
    }

    public void addCard(cards card){
        this.spacedCards.add(card);
        for(cards card1:spacedCards)
        {
        if (card.getCardName()==card1.getCardName())
            card.setCountingCards();

        }
    }
}
