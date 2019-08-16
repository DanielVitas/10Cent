package logic.boards;

import display.frame.DisplayObject;
import display.frame.misc.Dimension;
import logic.boards.finalBoard.FinalBoard;
import logic.game.GameController;
import logic.players.Token;
import logic.players.empty.Empty;
import logic.players.Player;
import logic.players.undecided.Undecided;

public abstract class Board extends DisplayObject {

    /*
    Board on which the game is played. Implementation of sub-boards is left to the final classes. Board serves visual
    purposes and is connected with LogicBoard, that serves game purposes. Other functions will mostly interact with
    Board, LogicBoard is used only for some calculations in Intelligence.
     */

    // two static players that mark empty and undecided slots
    public final static Player empty = new Empty();
    public final static Player undecided = new Undecided();

    public LogicBoard logicBoard;
    protected GameController gameController;  // is set in two-dimensional board
    public Dimension dimension;  // used when displaying board

    public Board(Dimension dimension) {
        this.dimension = dimension;
    }

    // returns true if move is legal
    public boolean play(Move move) {
        assert validMove(move);  // exception handling is not needed as those could arise only from bugs

        if (outcome() != empty)
            return false;

        if (logicBoard != null)
            logicBoard.play(move);

        Board subBoard = selectSubBoard(move);  // subBoard is null only if the current board is final
        if (subBoard != null)
            return subBoard.play(move.getNextMove());

        return true;  // this value is only used in FinalBoard's play
    }

    // returns player that won the board - undecided if there is no such player and board is full, otherwise empty
    public Player outcome() {
        return logicBoard.outcome();
    }

    // returns token currently hovered by the mouse
    public abstract Token getHoveredToken();

    // returns sub-board move is played on (the top one)
    protected abstract Board selectSubBoard(Move move);

    // returns token at passed move - there is always one (maybe EmptyToken)
    public Token getToken(Move move) {
        if (this instanceof FinalBoard)
            return ((FinalBoard) this).token;
        return selectSubBoard(move).getToken(move.getNextMove());  // assumes move is legal
    }

    // returns true if the move is valid - used for less confusion when illegal move is played (should never happen)
    protected abstract boolean validMove(Move move);

}
