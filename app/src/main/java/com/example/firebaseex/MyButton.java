package com.example.firebaseex;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;

public class MyButton extends androidx.appcompat.widget.AppCompatButton{
    private int x, y;
    private String letter;
    private Boolean isSetted;

    public MyButton(Context context, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
        this.isSetted=false;
    }

    public MyButton(Context context) {
        super(context);

    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Boolean getSetted() {
        return isSetted;
    }

    public void setSetted(Boolean setted) {
        isSetted = setted;
    }
}
