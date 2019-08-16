package logic.boards;

import logic.players.Player;

import java.util.Set;
import java.util.Stack;

public abstract class LogicBoard {

    /*
    This board is effectively playing board displayed with ordinary Board - separated so it's lighter as many instances
    of it will be crated with minimax algorithm.
     */

    // returns player that won the board (can be empty, undecided)
    public abstract Player outcome();

    // plays move
    public abstract void play(Move move);

    // obtains set of legal moves depending on previous move - deconstructed move is exactly that with poped final move
    public abstract Set<Move> legalMoves(Player player, Stack<Move> deconstructedPreviousMove);

    // returns set of all possible moves (with no regard to previous move) - player is set
    public abstract Set<Move> allMoves(Player player);

    // cloning is required for minimax
    public abstract LogicBoard clone();

}
