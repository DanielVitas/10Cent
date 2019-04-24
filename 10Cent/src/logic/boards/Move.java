package logic.boards;

public abstract class Move {

    /*
    Each Board comes with respective Move.
     */

    private Move nextMove;

    public Move() {

    }

    public Move getNextMove() {
        return nextMove;
    }

    public void setNextMove(Move move) {
        nextMove = move;
    }

}
