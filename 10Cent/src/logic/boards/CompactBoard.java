package logic.boards;

import logic.players.Player;

import java.util.Set;

public abstract class CompactBoard {

    /*
    This board is effectively playing board displayed with ordinary Board.
     */

    public CompactBoard() {

    }

    public abstract Player outcome();

    public abstract void play(Move move);

    public abstract Set<Move> legalMoves(Player player, Move previousMove);

    public abstract Set<Move> allMoves(Player player);

}
