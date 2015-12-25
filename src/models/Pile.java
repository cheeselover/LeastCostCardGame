package models;

import models.Card;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by siva on 2015-12-24.
 */
public class Pile {
    private Stack<Card> cards;
    private ArrayList<Card> available;

    public void reset(Card c) {
        cards = new Stack<>();
        available = new ArrayList<>();
        available.add(c);
    }

    public Card take(int index) {
        Card card = available.remove(index);

        for(Card c : available) {
            cards.add(c);
        }

        return card;
    }

    public void add(Card c) {
        available.add(c);
    }
}
