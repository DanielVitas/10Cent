package logic.players;

import display.frame.misc.Dimension;
import display.screens.Controller;
import logic.boards.Move;
import logic.game.GameController;
import logic.intelligence.Intelligence;
import logic.players.cross.Cross;
import logic.players.nought.Nought;

public abstract class Player {

    public Intelligence intelligence;

    public Player(Intelligence intelligence) {
        this.intelligence = intelligence;
    }

    public abstract Token newToken(Move move, GameController gameController, Dimension dimension);

    public static Player parseString(String string, Intelligence intelligence) {
        Player player = null;
        switch (string) {
            case Cross.NAME:
                player = new Cross(intelligence);
                break;
            case Nought.NAME:
                player = new Nought(intelligence);
                break;
        }
        return player;
    }

}
