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


    public int CheckWord(String str) {
        char c = str.charAt(0);

        if (c == 'א' || c == 'ב' || c == 'ג' || c == 'ד') {
            InputStream input = myButton.getResources().openRawResource(R.raw.words1);
            Scanner scan = new Scanner(input);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.equalsIgnoreCase(str.trim())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        if (c == 'ה') {
            InputStream input=myButton.getResources().openRawResource(R.raw.words2);
            Scanner scan = new Scanner(input);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.equalsIgnoreCase(str.trim())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        if (c == 'ח' || c == 'ט' || c == 'י' || c == 'כ' || c == 'ל') {
            InputStream input = myButton.getResources().openRawResource(R.raw.words3);
            Scanner scan = new Scanner(input);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.equalsIgnoreCase(str.trim())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
       else if (c == 'מ' || c == 'נ') {
            InputStream input = myButton.getResources().openRawResource(R.raw.words4);
            Scanner scan = new Scanner(input);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.equalsIgnoreCase(str.trim())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        if (c == 'ס' || c == 'ע' || c == 'פ' || c == 'צ' || c == 'ק' || c == 'ר' || c == 'ש' || c == 'ת') {
            InputStream input = myButton.getResources().openRawResource(R.raw.words5);
            Scanner scan = new Scanner(input);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.equalsIgnoreCase(str.trim())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        if (c == 'ו' || c == 'ז') {
            InputStream input = myButton.getResources().openRawResource(R.raw.words6);
            Scanner scan = new Scanner(input);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.equalsIgnoreCase(str.trim())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

}


