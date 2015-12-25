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

    public int makeCall(int playerIndex) {
        int currentPlayerPoints = this.players.get(playerIndex).calculatePoints();
        boolean successfulCall = true;
        int winningPlayerIndex = playerIndex;

        for(int i = 0; i < this.players.size(); i++) {
            if(i != playerIndex && this.players.get(i).calculatePoints() < currentPlayerPoints) {
                successfulCall = false;
                winningPlayerIndex = i;
            }
        }

        for(int i = 0; i < this.players.size(); i++) {
            Player p = this.players.get(i);

            if(i == playerIndex && !successfulCall) {
                p.addScore(50);
            } else if(i != winningPlayerIndex) {
                p.addScore(p.calculatePoints());
            }
        }

        return winningPlayerIndex;
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
