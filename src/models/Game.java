package models;

import java.util.ArrayList;

/**
 * Created by siva on 2015-12-24.
 */
public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Pile pile;

    public Game() {
        players = new ArrayList<>();
        deck = new Deck();
        pile = new Pile();
    }

    public void addHumanPlayer(String name) {
        players.add(new HumanPlayer(this, name));
    }

    public void addComputerPlayer() {
        players.add(new ComputerPlayer(this));
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

    public ArrayList<Player> getPlayers() {
        return this.players;
    }
}
