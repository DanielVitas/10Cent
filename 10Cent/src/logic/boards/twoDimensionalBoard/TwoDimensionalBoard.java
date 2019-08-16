package logic.boards.twoDimensionalBoard;

import display.frame.Mouse;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import logic.boards.Board;
import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.boards.finalBoard.FinalBoard;
import logic.game.GameController;
import logic.players.Token;

import java.awt.*;
import java.awt.event.MouseEvent;

import static display.frame.MainPanel.drawLine;

public class TwoDimensionalBoard extends Board {

    /*
    Squared tic-tac-toe board - it contains smaller boards.
     */

    private Board[][] boards;
    private int size;
    public Token token;
    // following two variables determine ratio between slot and edge
    private Dimension slotRelativeDimension = new Dimension(10, 10);
    private Dimension edgeRelativeDimension = new Dimension(1, 1);
    // actual dimensions for slot and edge
    private Dimension slotDimension;
    private Dimension edgeDimension;
    private Board hoveredSubBoard;  // currently hovered sub-board

    public TwoDimensionalBoard(Dimension dimension, Move move, GameController gameController, int size) {
        super(dimension);

        this.gameController = gameController;
        this.size = size;  // must be greater than one
        slotDimension = slotDimension();
        edgeDimension = edgeDimension();

        // constructor creates all the necessary boards and assigns moves to them
        boards = new Board[size][size];

        logicBoard = new TwoDimensionalLogicBoard(size) {
            @Override
            public LogicBoard installLogicBoard(int i, int j) {
                return installBoard(slotDimension,null).logicBoard;  // move does not matter here (, nor does dimension)
            }
        };

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                Move currentMove;
                if (move == null) {
                    currentMove = new TwoDimensionalMove(i, j);
                } else {
                    currentMove = move.clone();
                    currentMove.setNextMove(new TwoDimensionalMove(i, j));  // this board is not first in chain of moves (boards)
                }
                boards[i][j] = installBoard(slotDimension, currentMove);
                boards[i][j].coordinates = slotCoordinates(i, j);
            }
    }

    // these functions exist to simplify later styling and clean functional parts of code
    private Coordinates slotCoordinates(int i, int j) {
        double x = (slotDimension.width + edgeDimension.width) * i + edgeDimension.width;
        double y = (slotDimension.height + edgeDimension.height) * j + edgeDimension.height;
        return new Coordinates(x, y);
    }

    private Dimension slotDimension() {
        double widthRatio = slotRelativeDimension.width / ((slotRelativeDimension.width + edgeRelativeDimension.width) * size + edgeRelativeDimension.width);
        double heightRatio = slotRelativeDimension.height / ((slotRelativeDimension.height + edgeRelativeDimension.height) * size + edgeRelativeDimension.height);
        return new Dimension(widthRatio * dimension.width, heightRatio * dimension.height);
    }

    private Dimension edgeDimension() {
        double widthRatio = edgeRelativeDimension.width / ((slotRelativeDimension.width + edgeRelativeDimension.width) * size + edgeRelativeDimension.width);
        double heightRatio = edgeRelativeDimension.height / ((slotRelativeDimension.height + edgeRelativeDimension.height) * size + edgeRelativeDimension.height);
        return new Dimension(widthRatio * dimension.width, heightRatio * dimension.height);
    }

    // is meant to be overridden, by default it creates standard board
    protected Board installBoard(Dimension dimension, Move move) {
        return new FinalBoard(dimension, move, gameController);
    }

    @Override
    public boolean play(Move move) {
        if (!super.play(move))
            return false;

        if (outcome() != empty) {
            token = outcome().newToken(move, gameController, dimension);
            token.animatePlace();  // thread will sleep for the duration of the animation
        }

        return true;
    }

    @Override
    public Token getHoveredToken() {
        if (hoveredSubBoard == null)
            return null;
        return hoveredSubBoard.getHoveredToken();
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
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        if (token != null) {
            token.paint(coordinates, scale, g);
        } else {
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke((float) (0.2 * scale.average())));

            // paints lines
            for (int i = 1; i < size; i++) {
                Coordinates c1 = coordinates.add(slotCoordinates(i, 0).add(-edgeDimension.width / 2, -edgeDimension.height / 2).scale(scale));
                Coordinates c2 = coordinates.add(slotCoordinates(i, size).add(-edgeDimension.width / 2, -edgeDimension.height / 2).scale(scale));
                drawLine(c1, c2, g);
            }
            for (int j = 1; j < size; j++) {
                Coordinates c1 = coordinates.add(slotCoordinates(0, j).add(-edgeDimension.width / 2, -edgeDimension.height / 2).scale(scale));
                Coordinates c2 = coordinates.add(slotCoordinates(size, j).add(-edgeDimension.width / 2, -edgeDimension.height / 2).scale(scale));
                drawLine(c1, c2, g);
            }

            // paints sub-boards
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    boards[i][j].paint(coordinates.add(boards[i][j].coordinates.scale(scale)),
                            boards[i][j].dimension.getScale(slotDimension.scale(scale)), g);
        }
    }

    /*
    Overrides methods in same fashion as it's done in MainPanel - this is downside of having chained structure.
     */

    @Override
    public boolean contains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                Coordinates newCoordinates = boards[i][j].coordinates.scale(scale).flip().add(coordinates);
                if (boards[i][j].contains(newCoordinates, scale, mouseEvent)) {
                    if (!Mouse.pressed)
                        if (hoveredSubBoard != boards[i][j]) {
                            if (hoveredSubBoard != null)
                                hoveredSubBoard.unhover(coordinates, scale, mouseEvent);
                            hoveredSubBoard = boards[i][j];
                            hoveredSubBoard.hover(newCoordinates, scale, mouseEvent);
                        }
                    return true;
                }
            }
        if (hoveredSubBoard != null)
            hoveredSubBoard.unhover(coordinates, scale, mouseEvent);
        hoveredSubBoard = null;
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
        Coordinates newCoordinates = hoveredSubBoard.coordinates.scale(scale).flip().add(coordinates);
        hoveredSubBoard.press(newCoordinates, scale, mouseEvent);
    }

    @Override
    public void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredSubBoard == null)
            return;
        Coordinates newCoordinates = hoveredSubBoard.getCoordinates().scale(scale).flip().add(coordinates);
        hoveredSubBoard.release(newCoordinates, scale, mouseEvent);
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredSubBoard == null)
            return;
        Coordinates newCoordinates = hoveredSubBoard.getCoordinates().scale(scale).flip().add(coordinates);
        if (hoveredSubBoard.contains(newCoordinates, scale, mouseEvent))
            hoveredSubBoard.click(newCoordinates, scale, mouseEvent);
        else
            hoveredSubBoard.release(newCoordinates, scale, mouseEvent);
    }

}
