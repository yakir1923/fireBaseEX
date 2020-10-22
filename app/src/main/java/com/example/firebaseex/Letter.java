package com.example.firebaseex;

public class Letter {
    private String lett;
    private int scoring;
    private String icon;
    private Coord loc;


    public Letter(){
    }

    public Letter(String lett, int scoring, String i) {
        this.lett = lett;
        this.scoring = scoring;
        this.icon = i;
        loc  = new Coord(-1, -1);
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

    public Coord getLoc() { return loc; }

    public void setLoc(Coord c) {
        loc = c;
    }



    public boolean equals(Object obj) {
        if (!(obj instanceof Letter))
            return false;
        if (obj == this)
            return true;
        Letter c = (Letter) obj;
        return (this.lett == c.lett&& this.loc.equals(c.loc));
    }

    public int hashCode() {
        return 0;
    }

}

