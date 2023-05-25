package com.example.w23comp1008s1w5memorygame;

import java.util.ArrayDeque;
import java.util.Deque;

public class Player  {
    private String name;
    private Deque<Card> deck;

    public Player(String name) {
        this.name = name;
        deck = new ArrayDeque<>();
    }

    public String getName() {
        return name;
    }

    public Deque<Card> getDeck() {
        return deck;
    }

    public void addCard(Card card) {
        deck.addLast(card);
    }

    public boolean hasCards() {
        return !deck.isEmpty();
    }

    public Card playCard() {
        return deck.pollFirst();
    }
}