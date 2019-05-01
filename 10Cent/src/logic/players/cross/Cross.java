package logic.players.cross;

import display.frame.misc.Dimension;
import display.screens.Controller;
import logic.boards.Move;
import logic.game.GameController;
import logic.intelligence.Intelligence;
import logic.players.Player;
import logic.players.Token;

public class Cross extends Player {

    public Cross(Intelligence intelligence) {
        super(intelligence);
        sign = 'X';
    }

    @Override
    public Token newToken(Move move, GameController gameController, Dimension dimension) {
        return new CrossToken(this, move, gameController, dimension);
    }
}
