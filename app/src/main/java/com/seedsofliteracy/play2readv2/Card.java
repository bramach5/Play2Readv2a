package com.seedsofliteracy.play2readv2;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by bramach5 on 9/9/2016.
 */

public class Card {
    private String category;
    private String cardTxt;
    private int cardX;
    private int cardY;

    public Card(String pcat, String ptxt) {
        category = pcat;
        cardTxt = ptxt;
    }

    public void setX(int px) {
        cardX = px;
    }

    public void setY(int py) {
        cardY = py;
    }

    public String getCategory() {
        return category;
    }

    public String getCardTxt() {
        return cardTxt;
    }

    public int getCardX() {
        return cardX;
    }

    public int getCardY() {
        return cardY;
    }
}
