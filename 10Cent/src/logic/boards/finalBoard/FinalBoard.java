package logic.boards.finalBoard;

import display.frame.Coordinates;
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

    public FinalBoard() {
        super();
        token = Board.empty.newToken();  // creates new EmptyToken
        token.animateDefault();
        hitBoxes.add(new Rectangle(100, 100));
    }

    @Override
    public boolean play(Move move) throws InvalidMoveException {
        if (!super.play(move))
            return false;

        token = ((FinalMove) move).player.newToken();
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
    public void paint(Coordinates coordinates, Graphics g) {
        token.paint(coordinates, g);
    }

    @Override
    public void interact(Coordinates coordinates, MouseEvent mouseEvent) {
        System.out.println("Clicked.");
    }
}
