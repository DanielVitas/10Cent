package logic.boards.twoDimensionalBoard;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.boards.finalBoard.FinalLogicBoard;
import logic.players.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static logic.boards.Board.empty;
import static logic.boards.Board.undecided;

public class TwoDimensionalLogicBoard extends LogicBoard {

    /*
    This board is represented by double array of logic boards.
     */

    private LogicBoard[][] logicBoards;
    private Player outcome = empty;

    public TwoDimensionalLogicBoard(int size) {
        logicBoards = new LogicBoard[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                logicBoards[i][j] = installLogicBoard(i, j);
    }

    public LogicBoard[][] getLogicBoards() {
        return logicBoards;
    }

    // this should be overridden for more advanced boards
    public LogicBoard installLogicBoard(int i, int j) {
        return new FinalLogicBoard();
    }

    @Override
    public Player outcome() {
        if (outcome != empty)
            return outcome;

        int size = logicBoards.length;

        Player o;

        // checks all columns
        for (int i = 0; i < size; i++) {
            o = logicBoards[i][0].outcome();
            if (o != empty && o != undecided)
                block: {
                        for (int j = 1; j < size; j++)
                            if (o != logicBoards[i][j].outcome())
                                break block;
                        outcome = o;
                        return o;
                }
        }

        // checks all rows
        for (int j = 0; j < size; j++) {
            o = logicBoards[0][j].outcome();
            if (o != empty && o != undecided)
                block:{
                    for (int i = 1; i < size; i++)
                        if (o != logicBoards[i][j].outcome())
                            break block;
                    outcome = o;
                    return o;
                }
        }

        // checks both diagonals
        o = logicBoards[0][0].outcome();
        if (o != empty && o != undecided)
            block:{
                for (int i = 1; i < size; i++)
                    if (o != logicBoards[i][i].outcome())
                        break block;
                outcome = o;
                return o;
            }

        o = logicBoards[0][size - 1].outcome();
        if (o != empty && o != undecided)
            block:{
                for (int i = 1; i < size; i++)
                    if (o != logicBoards[i][size - 1 - i].outcome())
                        break block;
                outcome = o;
                return o;
            }

        if (allMovesEffective(empty).isEmpty()) {
            outcome = undecided;  // all slots have been filled
            return outcome;
        }

        return empty;
    }

    @Override
    public void play(Move move) {
        logicBoards[((TwoDimensionalMove) move).i][((TwoDimensionalMove) move).j].play(move.getNextMove());
    }

    @Override
    public Set<Move> legalMoves(Player player, Stack<Move> deconstructedPreviousMove) {
        if (outcome() != empty)
            return new HashSet<>();

        if (deconstructedPreviousMove.empty())
            return allMoves(player);  // happens only on first turn

        // determines on witch board the next move should take place
        Move previousMove = deconstructedPreviousMove.pop();  // can't be empty
        int i = ((TwoDimensionalMove) previousMove).i;
        int j = ((TwoDimensionalMove) previousMove).j;
        LogicBoard logicBoard = logicBoards[i][j];

        // if that board's outcome is already decided, player can move anywhere
        if (logicBoard.outcome() != empty)
            return allMoves(player);

        // any (valid - can't place token on already taken slot) move on determined sub-board is legal
        Set<Move> moves = new HashSet<>();
        Set<Move> subMoves = logicBoard.allMoves(player);

        for (Move subMove : subMoves) {
            Move move = new TwoDimensionalMove(i, j);  // starts chain in appropriate slot
            move.setNextMove(subMove);
            moves.add(move);
        }

        return moves;
    }

    @Override
    public Set<Move> allMoves(Player player) {
        if (outcome() != empty)
            return new HashSet<>();
        return allMovesEffective(player);
    }

    // doesn't check whether board is already decided
    private Set<Move> allMovesEffective(Player player) {
        Set<Move> moves = new HashSet<>();

        // populates moves with all moves from each sub-board
        for (int i = 0; i < logicBoards.length; i++)
            for (int j = 0; j < logicBoards[i].length; j++) {
                Set<Move> subMoves = logicBoards[i][j].allMoves(player);
                for (Move subMove : subMoves) {
                    Move move = new TwoDimensionalMove(i, j);
                    move.setNextMove(subMove);
                    moves.add(move);
                }
            }

        return moves;
    }

    @Override
    public LogicBoard clone() {
        TwoDimensionalLogicBoard clonedTwoDimensionalLogicBoard = new TwoDimensionalLogicBoard(logicBoards.length) {
            @Override
            public LogicBoard installLogicBoard(int i, int j) {
                return logicBoards[i][j].clone();
            }
        };
        clonedTwoDimensionalLogicBoard.outcome = outcome;
        return clonedTwoDimensionalLogicBoard;
    }

}
