package logic.players.empty;

import display.frame.misc.Dimension;
import logic.boards.Move;
import logic.game.GameController;
import logic.players.Player;
import logic.players.Token;

public class Empty extends Player {

    /*
    Marks the slots that can still be played on.
     */

    public Empty() {
        super(null);
    }

    @Override
    public Token newToken(Move move, GameController gameController, Dimension dimension) {
        return new EmptyToken(this, move, gameController, dimension);
    }

    @Override
    public String toString() {
        return "Empty";
    }

}
