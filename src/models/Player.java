package models;

import java.util.ArrayList;

/**
 * Created by siva on 2015-12-24.
 */
public abstract class Player {
    protected Game game;

    protected String name;
    protected boolean isHuman;
    protected Hand hand;
    private int score;

    protected Player(Game game) {
        this.game = game;
        this.score = 0;
        this.hand = new Hand();
    }

    public int getScore() { return this.score; }

    public void addScore(int score) {
        this.score += score;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHuman() { return this.isHuman; }

    public int getNumCards() { return this.hand.getNumCards(); }

    public void takeCard(Card c) { this.hand.add(c); }

    public void clearHand() { this.hand.reset(); }

    public ArrayList<Card> getHand() { return this.hand.getCards(); }

    public int calculatePoints() {
        int sum = 0;

        for(Card c : this.hand.getCards()) {
            sum += c.getPoints();
        }

        return sum;
    }

    public abstract ArrayList<Card> play(ArrayList<Integer> indexes);
}
