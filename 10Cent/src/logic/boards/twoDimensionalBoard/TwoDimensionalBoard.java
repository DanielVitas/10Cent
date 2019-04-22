package logic.boards.twoDimensionalBoard;

import logic.boards.Board;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.Move;
import logic.boards.exceptions.InvalidShapeException;
import logic.graphics.shapes.Rectangle;
import logic.graphics.shapes.Shape;

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
    public void paint(Shape area) throws InvalidShapeException {
        if (area instanceof Rectangle) {
            // narise se v respektiven kvader
        } else {
            throw new InvalidShapeException(this, area);
        }
    }

}
