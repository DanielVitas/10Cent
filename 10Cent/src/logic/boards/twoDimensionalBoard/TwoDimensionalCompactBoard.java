package logic.boards.twoDimensionalBoard;

import logic.boards.Board;
import logic.boards.CompactBoard;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.boards.finalBoard.FinalCompactBoard;
import logic.players.Player;

import java.util.HashSet;
import java.util.Set;

import static logic.boards.Board.empty;

public class TwoDimensionalCompactBoard extends CompactBoard {

    public CompactBoard[][] compactBoards;

    public TwoDimensionalCompactBoard(int size) {
        compactBoards = new CompactBoard[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                compactBoards[i][j] = installCompactBoard();
    }

    public CompactBoard installCompactBoard() {
        return new FinalCompactBoard();
    }

    @Override
    public Player outcome() {
        return empty;
    }

    @Override
    public void play(Move move) {
        compactBoards[((TwoDimensionalMove) move).i][((TwoDimensionalMove) move).j].play(move.getNextMove());
    }

    @Override
    public Set<Move> legalMoves(Player player, Move previousMove) {
        int i = ((TwoDimensionalMove) previousMove).i;
        int j = ((TwoDimensionalMove) previousMove).j;
        CompactBoard compactBoard = compactBoards[i][j];
        if (compactBoard.outcome() != empty)
            return allMoves(player);
        Set<Move> moves = new HashSet<>();
        Set<Move> subMoves = compactBoard.legalMoves(player, previousMove.getNextMove());
        for (Move subMove : subMoves) {
            Move move = new TwoDimensionalMove(i, j);
            move.setNextMove(subMove);
            moves.add(move);
        }
        return moves;
    }

    @Override
    public Set<Move> allMoves(Player player) {
        Set<Move> moves = new HashSet<>();
        for (int i = 0; i < compactBoards.length; i++)
            for (int j = 0; j < compactBoards[i].length; j++)
                if (compactBoards[i][j].outcome() == empty) {
                    Set<Move> subMoves = compactBoards[i][j].allMoves(player);
                    for (Move subMove : subMoves) {
                        Move move = new TwoDimensionalMove(i, j);
                        move.setNextMove(subMove);
                        moves.add(move);
                    }
                }
        return moves;
    }

}
