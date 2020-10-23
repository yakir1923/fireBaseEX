package com.example.firebaseex;

public class Letter {
    private String lett;
    private int scoring;
    private String icon;



    public Letter(){
    }

    public Letter(String lett, int scoring, String i) {
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

    public String getIcon() { return icon; }

    public void setIcon(String file) { icon = file;}




    public int hashCode() {
        return 0;
    }

}

