package com.example.w23comp1008s1w5memorygame;





import java.util.*;


public class DeckOfCards {
    private LinkedList<Card> deck;

    public DeckOfCards() {
        deck = new LinkedList<>();
        List<String> suits = Card.getValidSuits();
        List<String> faceNames = Card.getValidFaceNames();

        for (int i = 0; i < suits.size(); i++) {
            for (String faceName : faceNames)
                deck.addLast(new Card(faceName, suits.get(i)));
        }
    }


    public Card dealTopCard() {
        if (deck.size() > 0)
            return deck.removeFirst();
        else
            return null;
    }

    public void shuffle() {
        Card[] cards = deck.toArray(new Card[deck.size()]);
        Collections.shuffle(Arrays.asList(cards));
        deck.clear();
        deck.addAll(Arrays.asList(cards));
    }

    public int getNumOfCardsInDeck() {
        return deck.size();
    }

}