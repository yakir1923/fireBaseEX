package com.example.firebaseex;

import android.media.Image;

public class Letter {
    private char lett;
    private int scoring;
    private Image image;

    public Letter(){
    }
//3
    public Letter(char lett, int scoring, Image image) {
        this.lett = lett;
        this.scoring = scoring;
        this.image = image;
    }

    public char getLett() {
        return lett;
    }

    public void setLett(char lett) {
        this.lett = lett;
    }

    public int getScoring() {
        return scoring;
    }

    public void setScoring(int scoring) {
        this.scoring = scoring;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

