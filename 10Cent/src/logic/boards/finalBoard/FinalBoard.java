package logic.boards.finalBoard;

import logic.boards.Board;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.exceptions.InvalidShapeException;
import display.shape.Shape;
import display.shape.Rectangle;

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
    public void paint(Shape area) throws InvalidShapeException {
        if (area instanceof Rectangle) {
            // narise se v respektiven kvader
        } else {
            throw new InvalidShapeException(this, area);
        }
    }
}
