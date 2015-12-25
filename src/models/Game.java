package models;

import java.util.ArrayList;

/**
 * Created by siva on 2015-12-24.
 */
public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Pile pile;

    public static int startingTurn = 0;

    public Game() {
        players = new ArrayList<>();
        deck = new Deck();
        pile = new Pile();
    }

    public void start() {
        deck.reset();

        for(Player p : players) {
            p.clearHand();

            for(int i = 0; i < 5; i++) {
                p.takeCard(deck.draw());
            }
        }

        pile.reset(deck.draw());
    }

    public boolean makePlay(int playerIndex, ArrayList<Integer> playIndexes, int pickFromPile) {
        Player current = this.players.get(playerIndex);
        ArrayList<Card> playedCards = current.play(playIndexes);
        if(playedCards == null) return false;

        Card pickup;
        if(pickFromPile == -1) pickup = this.deck.draw();
        else pickup = this.pile.take(pickFromPile);
        current.takeCard(pickup);

        this.pile.add(playedCards);
        return true;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player removePlayer(int index) {
        return this.players.remove(index);
    }

    public void addHumanPlayer(String name) {
        players.add(new HumanPlayer(this, name));
    }

    public void addComputerPlayer() {
        players.add(new ComputerPlayer(this));
    }

    public ArrayList<Card> getAvaliablePile() { return this.pile.getAvailable(); }
}
