package logic.boards.finalBoard;

import display.Coordinates;
import logic.boards.Board;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FinalBoard extends Board {

    /*
    The most basic 1x1 board. Players effectively place their tokens onto this board.
     */

    public FinalBoard() {
        super();
    }

    @Override
    public boolean play(Move move) throws InvalidMoveException {
        if (!super.play(move))
            return false;

        setOutcome(((FinalMove) move).player);

        return true;
    }

    // FinalBoards is too simple for decideOutcome to have any meaning
    @Override
    protected void decideOutcome() {

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
    public void paint(Coordinates coordinates, Graphics g) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/images/image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, (int) coordinates.getX(), (int) coordinates.getX(), null);
    }
}
