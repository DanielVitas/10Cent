package logic.players;

import display.frame.misc.Dimension;
import logic.boards.Move;
import logic.game.GameController;
import logic.intelligence.Intelligence;
import logic.players.bird.Bird;
import logic.players.cross.Cross;
import logic.players.scratch.Scratch;
import logic.players.crossBloody.CrossBloody;
import logic.players.crown.Crown;
import logic.players.heart.Heart;
import logic.players.nought.Nought;
import logic.players.noughtBloody.NoughtBloody;

public abstract class Player {

    public Intelligence intelligence;

    public Player(Intelligence intelligence) {
        this.intelligence = intelligence;
    }

    public abstract Token newToken(Move move, GameController gameController, Dimension dimension);

    public static Player parsePlayer(String string, Intelligence intelligence) {
        Player player = null;
        switch (string) {
            case Cross.NAME:
                player = new Cross(intelligence);
                break;
            case Nought.NAME:
                player = new Nought(intelligence);
                break;
            case CrossBloody.NAME:
                player = new CrossBloody(intelligence);
                break;
            case NoughtBloody.NAME:
                player = new NoughtBloody(intelligence);
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
