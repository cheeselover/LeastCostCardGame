package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by siva on 2015-12-24.
 */
public class Hand {
    private ArrayList<Card> cards;

    public void reset() {
        this.cards = new ArrayList<>();
    }

    public void add(Card c) {
        this.cards.add(c);
    }
    public void add(int value, int suit) {
        this.add(new Card(value, suit));
    }

    public int getNumCards() {
        return this.cards.size();
    }

    public ArrayList<Card> play(ArrayList<Integer> indexes) {
        ArrayList<Card> playedCards = new ArrayList<>();

        for(int i : indexes) {
            playedCards.add(this.cards.get(i));
        }

        if(validatePlay(playedCards)) {
            // so that we remove last first (to keep ordering of indexes)
            Collections.sort(indexes, (Integer i1, Integer i2) -> {
                return i1.compareTo(i2) * -1;
            });

            for(int i : indexes) {
                this.cards.remove(i);
            }

            return playedCards;
        } else {
            System.out.println("GAME: Invalid play.");
            return null;
        }
    }

    private boolean validatePlay(ArrayList<Card> playedCards) {
        if(playedCards.size() == 1) return true;
        else if(playedCards.size() == 2) {
            return playedCards.get(0).getValue() == playedCards.get(1).getValue();
        } else {
            Card first = playedCards.get(0);

            // first check if all cards of the same value
            boolean allSameValue = true;

            for(int i = 1; i < playedCards.size(); i++) {
                if(playedCards.get(i).getValue() != first.getValue()) {
                    allSameValue = false;
                    break;
                }
            }

            if(allSameValue) return true;

            Collections.sort(playedCards, (Card c1, Card c2) -> {
                if(c1.getValue() < c2.getValue()) return -1;
                else if(c1.getValue() == c2.getValue()) return 0;
                else return 1;
            });

            boolean consecutiveOrder = true;
            first = playedCards.get(0);

            for(int i = 1; i < playedCards.size(); i++) {
                Card c = playedCards.get(i);
                if(c.getSuit() != first.getSuit() || c.getValue() != first.getValue() + i) {
                    consecutiveOrder = false;
                    break;
                }
            }

            return consecutiveOrder;
        }
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
