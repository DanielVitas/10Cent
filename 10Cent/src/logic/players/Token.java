package logic.players;

import display.frame.DisplayObject;
import display.frame.misc.Dimension;
import display.images.Animation;
import logic.boards.Move;
import logic.game.GameController;

import java.nio.file.Paths;

public abstract class Token extends DisplayObject {

    /*
    Tokens are visual marks on the board. One is created every time a player moves and it is bound to that move.
     */

    public Player player;
    protected String directoryPath;  // contains animation directories
    protected Dimension dimension;
    protected Move move;
    protected GameController gameController;

    public Token(Player player, Move move, GameController gameController, Dimension dimension, String directoryPath) {
        this.player = player;
        this.move = move;
        this.gameController = gameController;
        this.dimension = dimension;
        this.directoryPath = directoryPath;

        installAnimations();
    }

    protected void installAnimations() {
        Animation animation;
        String path;

        path = Paths.get(directoryPath, "default").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("default", animation);

        path = Paths.get(directoryPath, "place").toString();
        animation = new Animation(path, new long[]{200}, false) {
            @Override
            protected void finished() {
                animateDefault();
            }
        };
        animation.dimension = dimension;
        addAnimation("place", animation);
    }

    public void animateDefault() {
        animate("default");
    }

    public void animatePlace() {
        animate("place");
        animations.get("place").sleep();
    }

}
