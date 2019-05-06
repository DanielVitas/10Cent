package logic.players.crossBloody;

import display.frame.misc.Dimension;
import logic.boards.Move;
import logic.game.GameController;
import logic.intelligence.Intelligence;
import logic.players.Player;
import logic.players.Token;

public class CrossBloody extends Player {

    public final static String NAME = "Cross Bloody";

    public CrossBloody(Intelligence intelligence) {
        super(intelligence);
    }

    @Override
    public Token newToken(Move move, GameController gameController, Dimension dimension) {
        return new CrossBloodyToken(this, move, gameController, dimension);
    }

    @Override
    public String toString() {
        return NAME;
    }

}
