package logic.players.empty;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Animation;
import display.images.Images;
import display.screens.Controller;
import logic.boards.Move;
import logic.game.GameController;
import logic.players.Player;
import logic.players.Token;

import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.util.Set;

public class EmptyToken extends Token {

    private boolean waiting = false;
    private static Animation waitingAnimation;

    public EmptyToken(Player player, Move move, GameController gameController, Dimension dimension) {
        super(player, move, gameController, dimension, Paths.get(Images.RESOURCES_PATH,"images", "tokens", "empty").toString());
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
        animateDefault();
    }

    @Override
    public void installAnimations() {
        super.installAnimations();

        Animation animation;
        String path;

        if (waitingAnimation == null) {
            path = Paths.get(directoryPath, "waiting").toString();
            waitingAnimation = new Animation(path, new long[]{500}, true);
            waitingAnimation.isStatic = true;
            waitingAnimation.dimension = dimension;
        }
        addAnimation("waiting", waitingAnimation);

        path = Paths.get(directoryPath, "hovered").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("hovered", animation);

        path = Paths.get(directoryPath, "pressed").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("pressed", animation);
    }

    @Override
    public void animateDefault() {
        if (waiting)
            animate("waiting");
        else
            animate("default");
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (waiting)
            animate("hovered");
    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateDefault();
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (waiting)
            animate("pressed");
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        // first two conditions are redundant
        if (gameController.isLegal(move) && gameController.awaitingPlayer && waiting) {
            move.setPlayer(gameController.getCurrentPlayer());
            gameController.getCurrentPlayer().intelligence.close();
            gameController.currentMove = move;
        }
    }

}
