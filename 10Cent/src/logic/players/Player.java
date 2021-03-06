package logic.players;

import display.frame.misc.Dimension;
import logic.boards.Move;
import logic.game.GameController;
import logic.intelligence.Intelligence;
import logic.players.bird.Bird;
import logic.players.cross.Cross;
import logic.players.scratch.Scratch;
import logic.players.crown.Crown;
import logic.players.heart.Heart;
import logic.players.nought.Nought;

public abstract class Player {

    /*
    Main function of player is storing intelligence and tokens - latter are linked to the player. Structure of making a
    new class for every player might seem exhausting, but it was done to enable maximum customization - leaving final
    implementation of animations and other eye candies to each player.
     */

    public Intelligence intelligence;

    public Player(Intelligence intelligence) {
        if (intelligence != null)
            intelligence.setPlayer(this);
        this.intelligence = intelligence;
    }

    // returns instance of token belonging to this player
    public abstract Token newToken(Move move, GameController gameController, Dimension dimension);

    // returns instance of player depending on string given
    public static Player parsePlayer(String string, Intelligence intelligence) {
        Player player = null;
        switch (string) {
            case Cross.NAME:
                player = new Cross(intelligence);
                break;
            case Nought.NAME:
                player = new Nought(intelligence);
                break;
            case Heart.NAME:
                player = new Heart(intelligence);
                break;
            case Bird.NAME:
                player = new Bird(intelligence);
                break;
            case Crown.NAME:
                player = new Crown(intelligence);
                break;
            case Scratch.NAME:
                player = new Scratch(intelligence);
                break;
        }
        return player;
    }

}
