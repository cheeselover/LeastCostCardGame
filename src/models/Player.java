package models;

import java.util.ArrayList;

/**
 * Created by siva on 2015-12-24.
 */
public abstract class Player {
    protected Game game;

    protected String name;
    protected boolean isHuman;
    private int score;
    private Hand hand;

    protected Player(Game game) {
        this.game = game;
        this.score = 0;
        this.hand = new Hand();
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHuman() { return this.isHuman; }

    public int getNumCards() { return this.hand.getNumCards(); }

    public abstract ArrayList<Card> play(ArrayList<Integer> indexes);

    public void takeCard(Card c) { this.hand.add(c); }

    public void clearHand() { this.hand.reset(); }

}
