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

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.black);
        g2.setStroke(new BasicStroke(2f));

        // temporary
        for (int i = 1; i < size; i++) {
                Coordinates c1 = coordinates.add(slotCoordinates(i, 0).scale(scale));
                Coordinates c2 = coordinates.add(slotCoordinates(i, size).scale(scale));
                g.drawLine((int) c1.getX(), (int) c1.getY(), (int) c2.getX(), (int) c2.getY());
            }
        for (int j = 1; j < size; j++) {
            Coordinates c1 = coordinates.add(slotCoordinates(0, j).scale(scale));
            Coordinates c2 = coordinates.add(slotCoordinates(size, j).scale(scale));
            g.drawLine((int) c1.getX(), (int) c1.getY(), (int) c2.getX(), (int) c2.getY());
        }

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                boards[i][j].paint(coordinates.add(slotCoordinates(i, j).scale(scale)),
                        boards[i][j].getDimension().getScale(slotDimension.scale(scale)), g);
    }

    @Override
    public void clicked(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                Scale newScale = boards[i][j].getDimension().getScale(slotDimension.scale(scale));
                if (boards[i][j].contains(boardCoordinates[i][j].scale(scale).flip().add(coordinates), newScale)) {
                    boards[i][j].clicked(boardCoordinates[i][j].flip().scale(scale).add(coordinates), newScale, mouseEvent);
                    return;
                }
            }
    }

}
