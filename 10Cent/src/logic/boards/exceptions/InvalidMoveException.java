package logic.boards.exceptions;

import logic.boards.Board;
import logic.boards.Move;

public class InvalidMoveException extends Exception {

    /*
    When playing Move onto Board, move type must match boards otherwise this exception is thrown
     */

    public InvalidMoveException(Board board, Move move) {
        super("Move does not align with board.\nBoard: " + board + "\nMove: " + move);
    }

}
