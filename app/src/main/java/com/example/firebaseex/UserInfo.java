package com.example.firebaseex;

public class UserInfo {
    private  String name;
    private  String password;
    private  String email;
    private int level;

    public UserInfo() {
    }
//5
    public UserInfo(String name, String password, String email,int level) {
        this.name = name;
        this.password = password;
        this.email = email;

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
