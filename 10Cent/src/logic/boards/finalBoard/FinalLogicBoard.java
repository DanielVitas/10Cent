package logic.boards.finalBoard;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.players.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static logic.boards.Board.empty;

public class FinalLogicBoard extends LogicBoard {

    private Player player;

    public FinalLogicBoard() {
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
    public Set<Move> legalMoves(Player player, Stack<Move> deconstructedPreviousMove) {
        return allMoves(player);
    }

    @Override
    public Set<Move> allMoves(Player player) {
        if (outcome() != empty)
            return new HashSet<>();

        Set<Move> moves = new HashSet<>();
        moves.add(new FinalMove(player));
        return moves;
    }

    @Override
    public LogicBoard clone() {
        FinalLogicBoard clonedFinalLogicBoard = new FinalLogicBoard();
        clonedFinalLogicBoard.player = player;
        return clonedFinalLogicBoard;
    }

}
