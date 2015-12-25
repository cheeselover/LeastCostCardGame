package models;

import utils.Suit;
import utils.Value;

import java.util.Random;
import java.util.Stack;

/**
 * Created by siva on 2015-12-24.
 */
public class Deck {
    private Stack<Card> cards;

    public void reset() {
        cards = new Stack<>();
        Random rand = new Random();
        int j;

        for(int i = 0; i < 52; i++) {
            j = rand.nextInt(cards.size() + 1);
            Card c = new Card((i % 13) + 1, (i / 13) + 1);

            if(j == cards.size()) {
                cards.add(c);
            } else {
                cards.add(cards.get(j));
                cards.set(j, c);
            }
        }

        j = rand.nextInt(cards.size() + 1);
        Card joker = new Card(Value.JOKER, Suit.RED);

        if(j == cards.size()) {
            cards.add(joker);
        } else {
            cards.add(cards.get(j));
            cards.set(j, joker);
        }

        j = rand.nextInt(cards.size() + 1);
        joker = new Card(Value.JOKER, Suit.BLACK);

        if(j == cards.size()) {
            cards.add(joker);
        } else {
            cards.add(cards.get(j));
            cards.set(j, joker);
        }
    }

    public Card draw() {
        return cards.pop();
    }
}
