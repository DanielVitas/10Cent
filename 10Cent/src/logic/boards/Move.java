package logic.boards;

import logic.boards.finalBoard.FinalMove;
import logic.players.Player;

import java.util.Stack;

public abstract class Move {

    /*
    Each Board comes with respective Move. Move is linked to the move on the respective sub-board. This way moves form
    a chain.
     */

    private Move nextMove;

    public Move getNextMove() {
        return nextMove;
    }

    public void setNextMove(Move move) {
        if (nextMove == null)
            nextMove = move;
        else
            nextMove.setNextMove(move);
    }

    // player is effectively set in the FinalMove
    public void setPlayer(Player player) {
        if (this instanceof FinalMove)
            ((FinalMove) this).player = player;
        else
            nextMove.setPlayer(player);
    }

    // player from the final move in the chain - only one that has information about it
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

    // used when cloning boards
    public abstract Move clone();

}
