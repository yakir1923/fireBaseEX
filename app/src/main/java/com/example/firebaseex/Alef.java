package com.example.firebaseex;

public class Alef extends Letter {
    public Alef(String lett, int scoring, String i) {
        super(lett, scoring, i);
        this.setLett("א");
    }
    public Alef(){
        this.setLett("א");
        this.setScoring(10);
        this.setIcon("----");
    }

}
