package logic.boards;

import display.frame.DisplayObject;
import display.frame.misc.Dimension;
import logic.boards.exceptions.InvalidMoveException;
import logic.players.empty.Empty;
import logic.players.Player;

public abstract class Board extends DisplayObject {

    /*
    Board on which the game is played. Implementation of sub-boards is left to the final classes.
     */

    public static Player empty = new Empty();
    public Player outcome = empty;

    public Board() {

    }

    // returns true iff move is legal
    public boolean play(Move move) throws InvalidMoveException {
        if (!validMove(move))
            throw new InvalidMoveException(this, move);

        if (!(outcome instanceof Empty))
            return false;

        Board subBoard = selectSubBoard(move);
        if (subBoard != null)  // subBoard is null only if the current board is final
            return subBoard.play(move.getNextMove());

        decideOutcome();  // has no meaning in FinalBoard

        return true;  // this value is only used in FinalBoard's play
    }

    protected void setOutcome(Player player) {
        outcome = player;
    }

    protected abstract void decideOutcome();

    protected abstract Board selectSubBoard(Move move);

    protected abstract boolean validMove(Move move);

    public abstract Dimension getDimension();

}
