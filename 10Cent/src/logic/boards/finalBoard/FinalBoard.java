package logic.boards.finalBoard;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import logic.boards.Board;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.players.Token;

import java.awt.*;
import java.awt.event.MouseEvent;

public class FinalBoard extends Board {

    /*
    The most basic 1x1 board. Players effectively place their tokens onto this board.
     */

    public Token token;
    public Dimension slotDimension = new Dimension(10, 10);


    public FinalBoard() {
        super();
        token = Board.empty.newToken(slotDimension);  // creates new EmptyToken
        token.animateDefault();
        hitBoxes.add(new Rectangle(10, 10));
    }

    @Override
    public boolean play(Move move) throws InvalidMoveException {
        if (!super.play(move))
            return false;

        token = ((FinalMove) move).player.newToken(slotDimension);
        token.animatePlace();
        decideOutcome();

        return true;
    }

    //
    @Override
    protected void decideOutcome() {
        if (token.player != Board.empty)  // only to avoid setOutcome being called twice
            setOutcome(token.player);
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
    public Dimension getDimension() {
        return slotDimension;
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        token.paint(coordinates, scale, g);
    }

    @Override
    public void clicked(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        System.out.println("Clicked.");
    }
}
