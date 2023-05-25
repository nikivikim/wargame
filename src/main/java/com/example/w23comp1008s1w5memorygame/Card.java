package com.example.w23comp1008s1w5memorygame;

import javafx.scene.image.Image;

import java.util.*;

public class Card {

    private String faceName;
    private String suit;

    // Deque implementation using LinkedList
    private LinkedList<Card> deque = new LinkedList<>();

    // Constructor to create a Card instance
    public Card(String faceNumber, String suit) {
        setFaceName(faceNumber);
        setSuit(suit);
    }

    // Getters and Setters

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        faceName = faceName.toLowerCase();

        if (getValidFaceNames().contains(faceName))
            this.faceName = faceName;
        else
            throw new IllegalArgumentException(faceName + " must be in the list of " + getValidFaceNames());
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        suit = suit.toLowerCase();

        if (suit.charAt(suit.length() - 1) != 's')
            suit = suit + "s";

        if (getValidSuits().contains(suit))
            this.suit = suit;
        else
            throw new IllegalArgumentException(suit + " is not a valid suit, use one of " + getValidSuits());
    }

    // Other methods

    public static List<String> getValidFaceNames() {
        return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
    }

    public static List<String> getValidSuits() {
        return Arrays.asList("hearts", "diamonds", "spades", "clubs");
    }

    public int getCardValue() {List<String> faceNames = getValidFaceNames();
        int indexOfCard = faceNames.indexOf(faceName);
        return indexOfCard + 2;
    }

    public Image getImage() {
        String fileName = "images/" + faceName + "_of_" + suit + ".png";
        return new Image(Card.class.getResourceAsStream(fileName));
    }

    public Image getBackOfCardImage() {
        return new Image(Card.class.getResourceAsStream("images/back_of_card.png"));
    }

}