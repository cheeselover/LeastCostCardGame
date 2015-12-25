import models.Card;
import models.Game;
import models.Player;

import java.util.ArrayList;
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
            else if(input == 5) startGame();
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

    private void addPlayer() {
        System.out.print("Enter the player's name (or '#' for a computer player): ");
        String name = in.nextLine();
        if(name.equals("#")) this.game.addComputerPlayer();
        else this.game.addHumanPlayer(name);
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

    private void startGame() {
        System.out.println("Starting new game.\n--------------------");
        this.game.start();

        ArrayList<Player> players = this.game.getPlayers();
        int numPlayers = players.size();
        int turn = (Game.startingTurn) % numPlayers;
        Game.startingTurn = turn;

        while(true) {
            Player current = players.get(turn);
            ArrayList<Card> hand = current.getHand();

            System.out.println(current.getName() + "'s turn.");
            System.out.println("(0) - Call");
            for(int i = 0; i < hand.size(); i++) {
                System.out.println("(" + String.valueOf(i + 1) + ") - " + hand.get(i).toString());
            }
            in.nextLine();

            turn = (turn + 1) % numPlayers;
        }
    }
}
