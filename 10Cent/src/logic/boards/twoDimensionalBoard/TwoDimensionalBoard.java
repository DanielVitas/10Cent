package logic.boards.twoDimensionalBoard;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
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
    public Dimension slotDimension = new Dimension(10, 10);
    public Dimension edgeDimension = new Dimension(0.5, 0.5);

    public TwoDimensionalBoard(int size) {
        super();
        this.size = size;  // must be greater than one

        boards = new Board[size][size];
        boardCoordinates = new Coordinates[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                boards[i][j] = installBoard();
                boardCoordinates[i][j] = slotCoordinates(i, j).scale(new Scale(i, j));
            }
        hitBoxes.add(new Rectangle((int) getDimension().width, (int) getDimension().height));
    }

    private Coordinates slotCoordinates(int i, int j) {
        return new Coordinates((slotDimension.width + edgeDimension.width) * i + edgeDimension.width,
                (slotDimension.height + edgeDimension.height) * j + edgeDimension.height);
    }

    // is meant to be overridden, by default it creates standard board
    protected Board installBoard() {
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
    public Dimension getDimension() {
        Coordinates coordinates = slotCoordinates(size, size);
        return new Dimension(coordinates.getX(), coordinates.getY());
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                boards[i][j].paint(coordinates.add(slotCoordinates(i, j)),
                        boards[i][j].getDimension().getScale(slotDimension.scale(scale)), g);
    }

    @Override
    public void clicked(Coordinates coordinates, MouseEvent mouseEvent) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (boards[i][j].contains(boardCoordinates[i][j].flip().add(coordinates))) {
                    boards[i][j].clicked(boardCoordinates[i][j].flip().add(coordinates), mouseEvent);
                    return;
                }
    }

}
