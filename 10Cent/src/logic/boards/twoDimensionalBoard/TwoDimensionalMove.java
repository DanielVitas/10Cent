package logic.boards.twoDimensionalBoard;

import logic.boards.Move;

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

}
