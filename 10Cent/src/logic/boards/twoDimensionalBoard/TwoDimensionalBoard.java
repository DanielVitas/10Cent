package logic.boards.twoDimensionalBoard;

import display.Coordinates;
import logic.boards.Board;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.Move;

import java.awt.*;

public class TwoDimensionalBoard extends Board {

    @Override
    public boolean play(Move move) throws InvalidMoveException {
        return super.play(move);
    }

    @Override
    protected Board selectSubBoard(Move move) {
        return null;
    }

    @Override
    protected boolean validMove(Move move) {
        return move instanceof TwoDimensionalMove;
    }

    @Override
    public void paint(Coordinates coordinates, Graphics g) {

    }
}
