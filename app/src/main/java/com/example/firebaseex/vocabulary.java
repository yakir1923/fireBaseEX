package com.example.firebaseex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class vocabulary {

    private File file;
    private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    private ArrayList<String> allChecked = new ArrayList<String>();
    private ArrayList<String> legalChecked = new ArrayList<String>();

    public vocabulary(String filename) {
        file = new File(filename);
        saveWordsAlphabetically();
    }
// saves the words in the dictionary to arraylist
    private void saveWordsAlphabetically() {
        try {
            Scanner scanner = new Scanner(file);
            //checking if word is in the file
            String firstTwoLetters = null;
            ArrayList<String> currentLetterList = new ArrayList<String>(400);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().toLowerCase();
                if (firstTwoLetters == null)
                    firstTwoLetters = word.substring(0, 2);

                if (firstTwoLetters.equals(word.substring(0, 2)))
                    currentLetterList.add(word);
                else {
                    list.add(currentLetterList);
                    firstTwoLetters = word.substring(0, 2);
                    currentLetterList = new ArrayList<String>();
                    currentLetterList.add(word);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        }
        for (int i = list.size() - 1; i >= 0; i--)
            if (list.get(i).size() == 0)
                list.remove(i);
    }
//checks if the given word is a legal scrabble word  'word' word to check return true or false
    public boolean isWord(String word) {
        if (legalChecked.contains(word))
            return true;
        if (allChecked.contains(word) && !legalChecked.contains(word))
            return false;
        if (inDict(word)) {
            legalChecked.add(word);
            allChecked.add(word);
            return true;
        }
        allChecked.add(word);
        return false;
    }
//checks if the given word is in the supplied dictionary
    private boolean inDict(String word) {
        if (word.length() < 2)
            return false;
        String w = word.toLowerCase();
        char firstLetter = w.charAt(0);
        char secondLetter = w.charAt(1);
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).get(0).charAt(0) == firstLetter
                    && list.get(i).get(0).charAt(1) == secondLetter) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    if (list.get(i).get(j).toLowerCase().equals(w))
                        return true;
                }
                return false;
            }
        return false;
    }
}