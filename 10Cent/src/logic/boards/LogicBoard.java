package logic.boards;

import logic.players.Player;

import java.util.Set;
import java.util.Stack;

import static logic.boards.Board.empty;

public abstract class LogicBoard {

    /*
    This board is effectively playing board displayed with ordinary Board.
     */

    public LogicBoard() {

    }

    public abstract Player outcome();

    public abstract void play(Move move);

    public abstract Set<Move> legalMoves(Player player, Stack<Move> deconstructedPreviousMove);

    public abstract Set<Move> allMoves(Player player);

}
