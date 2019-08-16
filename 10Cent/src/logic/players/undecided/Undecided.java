package logic.players.undecided;

import display.frame.misc.Dimension;
import logic.boards.Move;
import logic.game.GameController;
import logic.players.Player;
import logic.players.Token;

public class Undecided extends Player {

    /*
    Marks the slots that are full and have no winner.
     */

    public Undecided() {
        super(null);
    }

    @Override
    public Token newToken(Move move, GameController gameController, Dimension dimension) {
        return new UndecidedToken(this, move, gameController, dimension);
    }

    @Override
    public String toString() {
        return "Undecided";
    }

}
