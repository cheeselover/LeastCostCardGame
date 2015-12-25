package models;

import java.util.ArrayList;

/**
 * Created by siva on 2015-12-24.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(Game game, String name) {
        super(game);
        this.name = name;
        this.isHuman = true;
    }

    @Override
    public ArrayList<Card> play(ArrayList<Integer> indexes) {
        return null;
    }
}
