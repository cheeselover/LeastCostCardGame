import models.Card;
import models.Game;
import models.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by siva on 2015-12-24.
 */
public class Controller {
    private Game game;
    private Scanner in;

    public Controller() {
        this.game = new Game();
        in = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the card game 'Least Cost'!");
        printMenu();

        System.out.print("Enter a command: ");
        int input = Integer.parseInt(in.nextLine());
        while(input != 1) {
            if(input == 2) printMenu();
            else if(input == 3) addPlayer();
            else if(input == 4) removePlayer();
            else if(input == 5) startNewGame();
            else System.out.println("MENU: Invalid command.");

            System.out.print("Enter a command: ");
            input = Integer.parseInt(in.nextLine());
        }
    }

    private void printMenu() {
        System.out.println("(1) - Quit");
        System.out.println("(2) - Show menu");
        System.out.println("(3) - Add player");
        System.out.println("(4) - Remove player");
        System.out.println("(5) - Start new game");
    }

    private void calculateScoreboard() {
        System.out.println("-SCOREBOARD-");
        ArrayList<Player> sortedPlayers = new ArrayList<>(this.game.getPlayers());

        Collections.sort(sortedPlayers, (Player p1, Player p2) -> {
            if(p1.getScore() < p2.getScore()) return 1;
            else if(p1.getScore() == p2.getScore()) return 0;
            else return -1;
        });

        for(int i = 0; i < sortedPlayers.size(); i++) {
            Player p = sortedPlayers.get(i);
            System.out.println("# " + String.valueOf(i + 1) + ": " + p.getName() + ", " + p.getScore() + " points");
        }
    }

    private void addPlayer() {
        System.out.print("Enter the player's name (or '#' for a computer player): ");
        String name = in.nextLine();

        if(name.equals("#")) {
            this.game.addComputerPlayer();
            name = "Computer Player";
        } else this.game.addHumanPlayer(name);

        System.out.println("Welcome, " + name + "!");
    }

    private void removePlayer() {
        System.out.println("Active players: ");
        ArrayList<Player> players = this.game.getPlayers();

        for(int i = 0; i < players.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ": " + players.get(i));
        }

        System.out.print("Enter the number of the player to remove: ");
        Player p = this.game.removePlayer(Integer.parseInt(in.nextLine()));
        System.out.println("Goodbye, " + p.getName() + "!");
    }

    private void playRound(int round) {
        System.out.println("--------------------\nRound " + round + " start!\n--------------------");
        this.game.reset();

        ArrayList<Player> players = this.game.getPlayers();
        int numPlayers = players.size();

        int turn = Game.startingTurn;
        Game.startingTurn = (turn + 1) % numPlayers;

        ArrayList<Integer> winningPlayers;

        boolean running = true;
        while(running) {
            Player current = players.get(turn);
            ArrayList<Card> hand = current.getHand();

            // TODO: rework this
            System.out.println(current.getName() + "'s turn.");

            // -----Entering the player's play from the hand-----
            System.out.println("-PLAY-");
            System.out.println("(-1) - Call");
            for(int i = 0; i < hand.size(); i++) {
                System.out.println("(" + String.valueOf(i) + ") - " + hand.get(i).toString());
            }

            System.out.print("Enter your play (space separated numbers): ");
            String[] line = in.nextLine().split(" ");
            ArrayList<Integer> indexes = new ArrayList<>();

            for(String s : line) {
                int play = Integer.parseInt(s);

                if(play == -1) {
                    winningPlayers = this.game.makeCall(turn);

                    if(winningPlayers.contains(turn)) {
                        System.out.println("Call success!  " + current.getName() + " wins.");
                    } else {
                        System.out.println("OH NO!  Wrong call, " + players.get(winningPlayers.get(0)).getName() + " had a better hand.");
                    }

                    running = false;
                    break;
                } else {
                    indexes.add(play);
                }
            }
            //---------------------------------------------------

            if(!running) break;

            // ----Entering the player's pickup from the pile----
            System.out.println("-PICKUP-");
            System.out.println("(-1) - Draw from deck");

            ArrayList<Card> availablePile = this.game.getAvaliablePile();
            for(int i = 0; i < availablePile.size(); i++) {
                System.out.println("(" + String.valueOf(i) + ") - " + availablePile.get(i).toString());
            }

            System.out.print("Enter the card to pickup: ");
            if(this.game.makePlay(turn, indexes, Integer.parseInt(in.nextLine()))) {
                System.out.println("Successful play!");
                turn = (turn + 1) % numPlayers;
            } else {
                System.out.println("Invalid play, try again.");
            }
            //---------------------------------------------------
        }

        System.out.println("Round " + round + " over!");
        calculateScoreboard();
        for(Player p : this.game.eliminatePlayers()) {
            System.out.println(p.getName() + " has been ELIMINATED!");
        }
    }

    private void startNewGame() {
        System.out.println("Starting new game.");

        int round = 1;

        while(this.game.getPlayers().size() > 1) {
            playRound(round++);

            for(int i = 0; i < this.game.getPlayers().size(); i++) {
                Player p = this.game.getPlayers().get(i);
                if(p.getScore() >= 150) {
                    System.out.println(p.getName() + " has been ELIMINATED!");
                    this.game.removePlayer(i);
                    i = (i == 0) ? 0 : i - 1;
                }
            }
        }

        System.out.println("Game over! " + this.game.getPlayers().get(0).getName() + " is the WINNER!\n--------------------");
    }
}
