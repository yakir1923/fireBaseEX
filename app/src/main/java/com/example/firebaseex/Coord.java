package com.example.firebaseex;

public class Coord {

    private int row;
    private int col;


    public Coord(int r, int c) {
        row = r;
        col = c;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coord))
            return false;
        if (obj == this)
            return true;
        Coord c = (Coord) obj;
        return (this.row == c.row && this.col == c.col);

    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        if (row < 0 || col < 0)
            return "(-,-)";
        return "(" + row + "," + col + ")";
    }
}
