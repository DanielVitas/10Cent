package logic.players;

import display.frame.misc.Dimension;
import display.screens.Controller;
import logic.boards.Move;
import logic.game.GameController;
import logic.intelligence.Intelligence;

public abstract class Player {

    public Intelligence intelligence;
    public char sign;

    public Player(Intelligence intelligence) {
        this.intelligence = intelligence;
    }

    public abstract Token newToken(Move move, GameController gameController, Dimension dimension);

}
