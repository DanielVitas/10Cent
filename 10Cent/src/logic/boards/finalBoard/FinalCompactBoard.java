package logic.boards.finalBoard;

import logic.boards.CompactBoard;
import logic.boards.Move;
import logic.players.Player;

import java.util.HashSet;
import java.util.Set;

import static logic.boards.Board.empty;

public class FinalCompactBoard extends CompactBoard {

    private Player player;

    public FinalCompactBoard() {
        player = empty;
    }

    @Override
    public Player outcome() {
        return player;
    }

    @Override
    public void play(Move move) {
        player = ((FinalMove) move).player;
    }

    @Override
    public Set<Move> legalMoves(Player player, Move previousMove) {
        Set<Move> moves = new HashSet<>();
        moves.add(new FinalMove(player));
        return moves;
    }

    @Override
    public Set<Move> allMoves(Player player) {
        return null;
    }

}
