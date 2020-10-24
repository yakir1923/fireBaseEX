package com.example.firebaseex;

public class cards {

    private String cardName;
    private int icon;
    private int countingCards;


    public cards(){
    }

    public cards(String cardName, int icon) {
        this.cardName = cardName;
        this.icon = icon;
        this.countingCards=0;


    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getCountingCards() {
        return countingCards;
    }

    public void setCountingCards() {
        this.countingCards++;
    }

    public void useCard(){ this.countingCards--; }
}
