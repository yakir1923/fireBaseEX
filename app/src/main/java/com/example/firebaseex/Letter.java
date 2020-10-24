package com.example.firebaseex;

public class Letter {
    private String lett;
    private int scoring;
    private int icon;



    public Letter(){
    }

    public Letter(String lett, int scoring, int i) {
        this.lett = lett;
        this.scoring = scoring;
        this.icon = i;

    }

    public String getLett() {
        return lett;
    }

    public void setLett(String lett) {
        this.lett = lett;
    }

    public int getScoring() {
        return scoring;
    }

    public void setScoring(int scoring) {
        this.scoring = scoring;
    }

    public int getIcon() { return icon; }

    public void setIcon(int file) { icon = file;}




    public int hashCode() {
        return 0;
    }

}

