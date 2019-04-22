package logic.boards.exceptions;

import logic.boards.Board;
import logic.boards.Move;

public class InvalidMoveException extends Exception {

    public InvalidMoveException(Board board, Move move) {
        super("Move does not align with board.\nBoard: " + board + "\nMove: " + move);
    }

}
