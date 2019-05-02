package logic.boards;

import logic.boards.finalBoard.FinalMove;
import logic.players.Player;

import java.util.Stack;

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

    public Player getPlayer() {
        if (this instanceof FinalMove)
            return ((FinalMove) this).player;
        else
            return nextMove.getPlayer();
    }

    // deconstructs move to sequence of moves (from most primitive - pop's first - to least primitive)
    public Stack<Move> deconstruct() {
        Stack<Move> stack = new Stack<>();
        stack.push(this);
        if (getNextMove() == null)
            return stack;
        stack.addAll(getNextMove().deconstruct());
        return stack;
    }

    public abstract Move clone();

}
