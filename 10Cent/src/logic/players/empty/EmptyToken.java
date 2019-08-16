package logic.players.empty;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Animation;
import display.images.Images;
import logic.boards.Move;
import logic.game.GameController;
import logic.players.Player;
import logic.players.Token;

import java.awt.event.MouseEvent;
import java.nio.file.Paths;

public class EmptyToken extends Token {

    /*
    Animations of empty token are a bit unique. It also responds to clicking.
     */

    private boolean waiting = false;  // if token can be clicked (or rather if it should respond to clicking)
    private static Animation waitingAnimation;  // all empty tokens share same animation, so it's synchronized

    EmptyToken(Player player, Move move, GameController gameController, Dimension dimension) {
        super(player, move, gameController, dimension, Paths.get(Images.RESOURCES_PATH,"images", "tokens", "empty").toString());
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
        if (gameController.board.getHoveredToken() == this)
            animate("hovered");
        else
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
        }
        waitingAnimation.dimension = dimension;
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
        if (gameController.awaitingPlayer && waiting) {
            gameController.awaitingPlayer = false;  // this is set from here, so two tokens can't be clicked at once
            gameController.currentPlayer().intelligence.close();  // this must also be done immediately
            move.setPlayer(gameController.currentPlayer());
            gameController.currentMove = move;
        }
    }

}
