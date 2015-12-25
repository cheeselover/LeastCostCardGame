package models;

import java.util.ArrayList;

/**
 * Created by siva on 2015-12-24.
 */
public class ComputerPlayer extends Player {
    private static int playerNum = 1;

    public ComputerPlayer(Game game) {
        super(game);
        this.name = "Computer" + String.valueOf(playerNum++);
        this.isHuman = false;
    }

    @Override
    public ArrayList<Card> play(ArrayList<Integer> indexes) {
        //TODO: implement the AI
        return null;
    }

}
