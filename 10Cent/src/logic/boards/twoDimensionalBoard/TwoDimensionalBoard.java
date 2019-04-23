package logic.boards.twoDimensionalBoard;

import logic.boards.Board;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.Move;

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
    public void paint(double x, double y) {

    }
}
