package logic.players.heart;

import display.frame.misc.Dimension;
import logic.boards.Move;
import logic.game.GameController;
import logic.intelligence.Intelligence;
import logic.players.Player;
import logic.players.Token;

public class Heart extends Player {

    public final static String NAME = "Heart";

    public Heart(Intelligence intelligence) {
        super(intelligence);
    }

    @Override
    public Token newToken(Move move, GameController gameController, Dimension dimension) {
        return new HeartToken(this, move, gameController, dimension);
    }

    @Override
    public String toString() {
        return NAME;
    }

}
