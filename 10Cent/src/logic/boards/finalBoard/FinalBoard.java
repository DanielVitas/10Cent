package logic.boards.finalBoard;

import display.frame.Mouse;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.screens.Controller;
import logic.boards.Board;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.game.GameController;
import logic.players.Token;

import java.awt.*;
import java.awt.event.MouseEvent;

public class FinalBoard extends Board {

    /*
    The most basic 1x1 board. Players effectively place their tokens onto this board.
     */

    public Token token;
    public Dimension slotDimension = new Dimension(10, 10);

    public FinalBoard(Move move, GameController gameController) {
        super();

        this.gameController = gameController;

        Move currentMove;
        if (move == null) {
            currentMove = new FinalMove(empty);
        } else {
            currentMove = move.clone();
            currentMove.setNextMove(new FinalMove(empty));
        }
        token = Board.empty.newToken(currentMove, gameController, slotDimension);  // creates new EmptyToken
        token.animateDefault();

        compactBoard = new FinalCompactBoard();

        hitBoxes.add(new Rectangle(10, 10));
    }

    @Override
    public boolean play(Move move) throws InvalidMoveException {
        if (!super.play(move))
            return false;

        token = ((FinalMove) move).player.newToken(move, gameController, slotDimension);
        token.animatePlace();

        return true;
    }

    @Override
    protected Board selectSubBoard(Move move) {
        return null;
    }

    @Override
    protected boolean validMove(Move move) {
        return move instanceof FinalMove;
    }

    @Override
    public Dimension getDimension() {
        return slotDimension;
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        token.paint(coordinates, scale, g);
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        token.hover(coordinates, scale, mouseEvent);
    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        token.unhover(coordinates, scale, mouseEvent);
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        token.press(coordinates, scale, mouseEvent);
    }

    @Override
    public void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        token.release(coordinates, scale, mouseEvent);
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        token.click(coordinates, scale, mouseEvent);
    }

}
