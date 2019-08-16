package logic.boards.twoDimensionalBoard;

import logic.boards.Move;

public class TwoDimensionalMove extends Move {

    int i, j;

    TwoDimensionalMove(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public Move clone() {
        TwoDimensionalMove twoDimensionalMove = new TwoDimensionalMove(i, j);
        if (getNextMove() != null)
            twoDimensionalMove.setNextMove(getNextMove().clone());
        return twoDimensionalMove;
    }

}
