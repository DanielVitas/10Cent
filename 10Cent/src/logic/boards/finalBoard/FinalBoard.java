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
    1x1 board, only one that can assume final value.
     */

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
