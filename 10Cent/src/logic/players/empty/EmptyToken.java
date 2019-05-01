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

public class EmptyToken extends Token {

    public EmptyToken(Player player, Move move, GameController gameController, Dimension dimension) {
        super(player, move, gameController, dimension, Paths.get(Images.RESOURCES_PATH,"images", "tokens", "empty").toString());
    }

    @Override
    public void installAnimations() {
        super.installAnimations();

        Animation animation;
        String path;
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
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        super.hover(coordinates, scale, mouseEvent);
        animate("hovered");
    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        super.hover(coordinates, scale, mouseEvent);
        animateDefault();
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        super.press(coordinates, scale, mouseEvent);
        animate("pressed");
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        move.setPlayer(gameController.getCurrentPlayer());
        gameController.currentMove = move;
    }

}
