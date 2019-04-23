package logic.boards.exceptions;

import logic.boards.Board;
import display.shape.Shape;

public class InvalidShapeException extends Exception {

    public InvalidShapeException(Board board, Shape shape) {
        super("Board cannot be painted onto specified shape.\nBoard: " + board + "\nShape: " + shape);
    }

}
