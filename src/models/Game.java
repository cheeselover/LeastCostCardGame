package models;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public void reset() {
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

    public ArrayList<Integer> makeCall(int playerIndex) {
        int lowestPoints = this.players.get(playerIndex).calculatePoints();
        boolean successfulCall = true;
        ArrayList<Integer> winningPlayers = new ArrayList<>();

        for(int i = 0; i < this.players.size(); i++) {
            if(i != playerIndex && this.players.get(i).calculatePoints() < lowestPoints) {
                successfulCall = false;
                winningPlayers.clear();
                winningPlayers.add(i);
            } else if(!successfulCall && this.players.get(i).calculatePoints() == lowestPoints) {
                winningPlayers.add(i);
            }
        }

        for(int i = 0; i < this.players.size(); i++) {
            Player p = this.players.get(i);

            if(i == playerIndex && !successfulCall) {
                p.addScore(50);
            } else if(!winningPlayers.contains(i)) {
                p.addScore(p.calculatePoints());
            }
        }

        return winningPlayers;
    }

    public ArrayList<Player> eliminatePlayers() {
        ArrayList<Player> eliminated = new ArrayList<>();

        this.players = this.players.stream()
                .filter(p -> {
                    if(p.getScore() < 150) {
                        return true;
                    } else {
                        eliminated.add(p);
                        return false;
                    }
                }).collect(Collectors.toCollection(ArrayList<Player>::new));

        return eliminated;
    }

    public ArrayList<Player> getPlayers() { return this.players; }

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
