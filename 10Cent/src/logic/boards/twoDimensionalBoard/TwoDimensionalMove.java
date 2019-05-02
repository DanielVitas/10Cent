package logic.boards.twoDimensionalBoard;

import logic.boards.Move;
import logic.boards.finalBoard.FinalMove;

public class TwoDimensionalMove extends Move {

    public int i, j;

    public TwoDimensionalMove(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public Move clone() {
        TwoDimensionalMove twoDimensionalMove = new TwoDimensionalMove(i, j);
        twoDimensionalMove.setNextMove(getNextMove());
        return twoDimensionalMove;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TwoDimensionalMove))
            return false;
        return i == ((TwoDimensionalMove) o).i && j == ((TwoDimensionalMove) o).j &&
                getNextMove().equals(((TwoDimensionalMove) o).getNextMove());
    }

}
