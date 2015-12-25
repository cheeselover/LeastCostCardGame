package models;

import utils.Suit;
import utils.Value;

/**
 * Created by siva on 2015-12-24.
 */
public class Card {
    private int value;
    private int suit;

    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSuit() {
        return this.suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        String v, s;

        if(this.value == Value.JOKER) v = "Joker";
        else if(this.value == Value.ACE) v = "Ace";
        else if(this.value <= 10) v = Integer.toString(this.value);
        else if(this.value == Value.JACK) v = "Jack";
        else if(this.value == Value.QUEEN) v = "Queen";
        else v = "King";

        if(this.suit == Suit.RED) s = "Red";
        else if(this.suit == Suit.BLACK) s = "Black";
        else if(this.suit == Suit.CLUBS) s = "Clubs";
        else if(this.suit == Suit.SPADES) s = "Spades";
        else if(this.suit == Suit.HEARTS) s = "Hearts";
        else s = "Diamonds";

        if(this.value == Value.JOKER) return s + " " + v;
        else return v + " of " + s;
    }
}
