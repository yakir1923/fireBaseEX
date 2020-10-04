package com.example.firebaseex;

public class UserInfo {
    private  String name;
    private  String lastName;
    private  String email;
    private int level;

    public UserInfo() {
    }
//5
    public UserInfo(String name, String lastName, String email,int level) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.level=level;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}