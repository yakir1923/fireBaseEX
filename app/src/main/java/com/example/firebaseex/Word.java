package com.example.firebaseex;

import android.content.res.Resources;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Scanner;

public class Word {
    private MyButton myButton;
    private String word;

    public Word(MyButton myButton,  String word) {
        this.myButton = myButton;
        this.word = word;
    }
    public Word(){

    }

    public MyButton getMyButton() {
        return myButton;
    }

    public void setMyButton(MyButton myButton) {
        this.myButton = myButton;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }




}


