package logic.boards.finalBoard;

import logic.boards.Board;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;

public class FinalBoard extends Board {

    public FinalBoard() {
        super();
    }

    @Override
    public boolean play(Move move) throws InvalidMoveException {
        super.play(move);
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
    public void paint(double x, double y) {

    }
}
