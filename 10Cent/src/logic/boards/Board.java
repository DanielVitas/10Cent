package logic.boards;

import display.frame.DisplayObject;
import display.frame.misc.Dimension;
import display.screens.Controller;
import logic.boards.exceptions.InvalidMoveException;
import logic.game.GameController;
import logic.players.Token;
import logic.players.empty.Empty;
import logic.players.Player;

public abstract class Board extends DisplayObject {

    /*
    Board on which the game is played. Implementation of sub-boards is left to the final classes.
     */

    public static Player empty = new Empty();
    public CompactBoard compactBoard;
    protected GameController gameController;

    public Board() {

    }

    public CompactBoard getCompactBoard() {
        return compactBoard;
    }

    // returns true iff move is legal - change to void?
    public boolean play(Move move) throws InvalidMoveException {
        if (!validMove(move))
            throw new InvalidMoveException(this, move);

        if (outcome() != empty)
            return false;

        Board subBoard = selectSubBoard(move);

        if (compactBoard != null)
            compactBoard.play(move);

        if (subBoard != null)  // subBoard is null only if the current board is final
            return subBoard.play(move.getNextMove());

        return true;  // this value is only used in FinalBoard's play
    }

    public Player outcome() {
        return compactBoard.outcome();
    }

    protected abstract Board selectSubBoard(Move move);

    protected abstract boolean validMove(Move move);

    public abstract Dimension getDimension();

}
