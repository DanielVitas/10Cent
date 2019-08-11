package logic.boards;

import logic.players.Player;

import java.util.Set;
import java.util.Stack;

public abstract class LogicBoard {

    /*
    This board is effectively playing board displayed with ordinary Board - separated so it's lighter as many instances
    of it will be crated with mini-max algorithm.
     */

    public LogicBoard() {

    }

    public abstract Player outcome();

    public abstract void play(Move move);

    // obtains set of legal moves depending on previous move, player is necessary as move is always bound to one
    public abstract Set<Move> legalMoves(Player player, Stack<Move> deconstructedPreviousMove);

    // returns set of all possible moves (with no regard to previous move)
    public abstract Set<Move> allMoves(Player player);

    public abstract LogicBoard clone();

}
