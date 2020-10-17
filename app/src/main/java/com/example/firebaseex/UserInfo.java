package com.example.firebaseex;

public class UserInfo {
    private  String name;
    private  String password;
    private  String email;
    private int level;
    private int userLose;
    private int userWin;

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
}
