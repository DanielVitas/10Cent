package logic.boards;

import logic.boards.finalBoard.FinalMove;
import logic.players.Player;

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
        if (nextMove == null)
            nextMove = move;
        else
            nextMove.setNextMove(move);
    }

    public void setPlayer(Player player) {
        if (this instanceof FinalMove)
            ((FinalMove) this).player = player;
        else
            nextMove.setPlayer(player);
    }

    public abstract Move clone();

}
