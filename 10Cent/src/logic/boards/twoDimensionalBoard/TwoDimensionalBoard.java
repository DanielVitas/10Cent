package logic.boards.twoDimensionalBoard;

import display.frame.Coordinates;
import logic.boards.Board;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.Move;
import logic.boards.finalBoard.FinalBoard;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TwoDimensionalBoard extends Board {

    /*
    Squared tic-tac-toe board.
     */

    private Board[][] boards;
    private Coordinates[][] boardCoordinates;
    private int size;

    public TwoDimensionalBoard(int size) {
        super();
        this.size = size;  // must be greater than one

        boards = new Board[size][size];
        boardCoordinates = new Coordinates[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                boards[i][j] = installBoard();
                boardCoordinates[i][j] = new Coordinates(110 * i, 110 * j);
            }
        hitBoxes.add(new Rectangle(110 * size - 10, 110 * size - 10));
    }

    // is meant to be overridden, by default it creates standard board
    public Board installBoard() {
        return new FinalBoard();
    }

    @Override
    public boolean play(Move move) throws InvalidMoveException {
        return super.play(move);
    }

    @Override
    protected void decideOutcome() {



    }

    @Override
    protected Board selectSubBoard(Move move) {
        return boards[((TwoDimensionalMove) move).i][((TwoDimensionalMove) move).j];
    }

    @Override
    protected boolean validMove(Move move) {
        if (move instanceof TwoDimensionalMove)
            return ((TwoDimensionalMove) move).i < size && ((TwoDimensionalMove) move).j < size;
        return false;
    }

    @Override
    public void paint(Coordinates coordinates, Graphics g) {
        super.paint(coordinates, g);

        // temporary
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                boards[i][j].paint(coordinates.add(110 * i, 110 * j), g);
    }

    @Override
    public void interact(Coordinates coordinates, MouseEvent mouseEvent) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (boards[i][j].contains(boardCoordinates[i][j].flip().add(coordinates))) {
                    boards[i][j].interact(boardCoordinates[i][j].flip().add(coordinates), mouseEvent);
                    return;
                }
    }

}
