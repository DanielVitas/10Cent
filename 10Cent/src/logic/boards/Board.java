package logic.boards;

import display.DisplayComponent;
import logic.boards.exceptions.InvalidMoveException;
import logic.players.Empty;
import logic.players.Player;

public abstract class Board implements DisplayComponent {

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
        return false;  // this value is not used, instead FinalBoard decides weather the move was legal
    }

    protected abstract Board selectSubBoard(Move move);

    protected abstract boolean validMove(Move move);

}
