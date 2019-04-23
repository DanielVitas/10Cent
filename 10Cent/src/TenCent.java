import logic.boards.Board;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.Move;
import logic.boards.exceptions.InvalidShapeException;
import logic.boards.finalBoard.FinalMove;
import logic.boards.twoDimensionalBoard.TwoDimensionalBoard;
import display.shape.Rectangle;
import display.shape.Shape;

public class TenCent {

    public static void main(String[] args) throws InvalidMoveException, InvalidShapeException {

        Board b = new TwoDimensionalBoard();
        Move m = new FinalMove();
        Shape s = new Rectangle(0,0,1,1){};
        b.paint(s);

    }

}
