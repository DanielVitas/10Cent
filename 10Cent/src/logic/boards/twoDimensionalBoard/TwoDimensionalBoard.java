package logic.boards.twoDimensionalBoard;

import display.frame.Mouse;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import logic.boards.Board;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.Move;
import logic.boards.finalBoard.FinalBoard;
import logic.players.Token;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TwoDimensionalBoard extends Board {

    /*
    Squared tic-tac-toe board.
     */

    private Board[][] boards;
    private int size;
    private Dimension slotDimension = new Dimension(10, 10);
    private Dimension edgeDimension = new Dimension(0.5, 0.5);
    private Board hoveredSubBoard;

    public TwoDimensionalBoard(int size) {
        super();
        this.size = size;  // must be greater than one

        boards = new Board[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                boards[i][j] = installBoard();
                boards[i][j].coordinates = slotCoordinates(i, j)/*.scale(new Scale(i, j))*/;
            }
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

        // paints lines
        for (int i = 1; i < size; i++) {
                Coordinates c1 = coordinates.add(slotCoordinates(i, 0).scale(scale));
                Coordinates c2 = coordinates.add(slotCoordinates(i, size).scale(scale));
                g.drawLine(c1.getIntegerX(), c1.getIntegerY(), c2.getIntegerX(), c2.getIntegerY());
            }
        for (int j = 1; j < size; j++) {
            Coordinates c1 = coordinates.add(slotCoordinates(0, j).scale(scale));
            Coordinates c2 = coordinates.add(slotCoordinates(size, j).scale(scale));
            g.drawLine(c1.getIntegerX(), c1.getIntegerY(), c2.getIntegerX(), c2.getIntegerY());
        }

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                boards[i][j].paint(coordinates.add(boards[i][j].coordinates.scale(scale)),
                        boards[i][j].getDimension().getScale(slotDimension.scale(scale)), g);
    }

    /*
    Overrides methods in same fashion as it's done in MainPanel.
     */

    @Override
    public boolean contains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                Scale newScale = boards[i][j].getDimension().getScale(slotDimension.scale(scale));
                Coordinates newCoordinates = boards[i][j].coordinates.scale(scale).flip().add(coordinates);
                if (boards[i][j].contains(newCoordinates, newScale, mouseEvent)) {
                    if (hoveredSubBoard != boards[i][j]) {
                        if (hoveredSubBoard != null)
                            hoveredSubBoard.unhover(coordinates, scale, mouseEvent);
                        hoveredSubBoard = boards[i][j];
                        hoveredSubBoard.hover(newCoordinates, newScale, mouseEvent);
                    }
                    return true;
                }
            }
        if (hoveredSubBoard != null)
            hoveredSubBoard.unhover(coordinates, scale, mouseEvent);
        return false;
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredSubBoard != null)
            hoveredSubBoard.hover(coordinates, scale, mouseEvent);
    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredSubBoard != null)
            hoveredSubBoard.unhover(coordinates, scale, mouseEvent);
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredSubBoard == null)
            return;
        Scale newScale = hoveredSubBoard.getDimension().getScale(slotDimension.scale(scale));
        Coordinates newCoordinates = hoveredSubBoard.coordinates.scale(scale).flip().add(coordinates);
        hoveredSubBoard.press(newCoordinates, newScale, mouseEvent);
    }

    @Override
    public void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredSubBoard == null)
            return;
        Scale newScale = hoveredSubBoard.getDimension().getScale(slotDimension.scale(scale));
        Coordinates newCoordinates = hoveredSubBoard.getCoordinates().scale(scale).flip().add(coordinates);
        hoveredSubBoard.release(newCoordinates, newScale, mouseEvent);
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredSubBoard == null)
            return;
        Scale newScale = hoveredSubBoard.getDimension().getScale(slotDimension.scale(scale));
        Coordinates newCoordinates = hoveredSubBoard.getCoordinates().scale(scale).flip().add(coordinates);
        if (hoveredSubBoard.contains(newCoordinates, newScale, mouseEvent))
            hoveredSubBoard.click(newCoordinates, newScale, mouseEvent);
        else
            hoveredSubBoard.release(newCoordinates, newScale, mouseEvent);
    }

}
