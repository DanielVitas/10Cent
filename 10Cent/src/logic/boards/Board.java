package logic.boards;

import display.frame.DisplayObject;
import display.frame.misc.Dimension;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.finalBoard.FinalBoard;
import logic.game.GameController;
import logic.players.Token;
import logic.players.empty.Empty;
import logic.players.Player;
import logic.players.undecided.Undecided;

public abstract class Board extends DisplayObject {

    /*
    Board on which the game is played. Implementation of sub-boards is left to the final classes.
     */

    public final static Player empty = new Empty();
    public final static Player undecided = new Undecided();

    public LogicBoard logicBoard;
    protected GameController gameController;
    public Dimension dimension;

    public Board(Dimension dimension) {
        this.dimension = dimension;
    }

    // returns true iff move is legal - change to void?
    public boolean play(Move move) throws InvalidMoveException {
        if (!validMove(move))
            throw new InvalidMoveException(this, move);

        if (outcome() != empty)
            return false;

        Board subBoard = selectSubBoard(move);

        if (logicBoard != null)
            logicBoard.play(move);

        if (subBoard != null)  // subBoard is null only if the current board is final
            return subBoard.play(move.getNextMove());

        return true;  // this value is only used in FinalBoard's play
    }

    public Player outcome() {
        return logicBoard.outcome();
    }

    protected abstract Board selectSubBoard(Move move);

    public Token getToken(Move move) {
        if (this instanceof FinalBoard)
            return ((FinalBoard) this).token;
        return selectSubBoard(move).getToken(move.getNextMove());  // assumes move is legal
    }

    protected abstract boolean validMove(Move move);

}
