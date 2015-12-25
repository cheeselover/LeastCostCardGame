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
        String input = in.next();
        while(input != "q") {
            if(input == "p") addPlayer();
            else if(input == "r") removePlayer();
            else if(input == "q") startGame();
            else System.out.println("MENU: Invalid command.");

            System.out.print("Enter a command: ");
            input = in.next();
        }
    }

    private void printMenu() {
        System.out.println("(q) - Quit");
        System.out.println("(o) - Show menu");
        System.out.println("(p) - Add player");
        System.out.println("(r) - Remove player");
        System.out.println("(g) - Start new game");
    }

    private void addPlayer() {
        System.out.print("Enter the player's name (or '#' for a computer player): ");
        String name = in.nextLine();
        if(name == "#") this.game.addComputerPlayer();
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
        Player p = this.game.removePlayer(in.nextInt());
        System.out.println("Goodbye, " + p.getName() + "!");
    }
}
