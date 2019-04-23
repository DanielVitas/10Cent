package logic.boards;

import display.DisplayComponent;
import logic.boards.exceptions.InvalidMoveException;

public abstract class Board implements DisplayComponent {

    public Board() {

    }

    /* vrne true ce je move bil vspesno odigran, false v nasprotnem primeru */
    public boolean play(Move move) throws InvalidMoveException {
        if (!validMove(move))
            throw new InvalidMoveException(this, move);

        Board subBoard = selectSubBoard(move);
        if (subBoard != null)  // lahko bi dal pogoj tudi nextMove != null, oboje mora biti izpolnjeno
            subBoard.play(move.getNextMove());
        return true;
    }

    protected abstract Board selectSubBoard(Move move);

    protected abstract boolean validMove(Move move);

}
