package logic.game;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.boards.finalBoard.FinalLogicBoard;
import logic.boards.twoDimensionalBoard.TwoDimensionalLogicBoard;
import logic.intelligence.Human;
import logic.players.Player;

import java.util.Set;
import java.util.Stack;

import static display.frame.MainFrame.targetedFrameRate;
import static logic.boards.Board.empty;
import static logic.boards.Board.undecided;

public abstract class StandardGameController extends GameController {

    /*
    Implementation of GameController. It's suited for tic-tac-toe and ultimate tic-tac-toe.
     */

    public StandardGameController(Player[] players) {
        super(players);
    }


    public static Set<Move> legalMoves(LogicBoard logicBoard, Move previousMove) {
        if (previousMove == null)
            return logicBoard.allMoves(empty);
        Stack<Move> deconstructedPreviousMove = previousMove.deconstruct();
        deconstructedPreviousMove.pop(); // pop's final move
        return logicBoard.legalMoves(empty, deconstructedPreviousMove);
    }

    public static Set<Move> legalMoves(LogicBoard logicBoard, Stack<Move> previousMoves) {
        if(previousMoves.empty())
            return legalMoves(logicBoard, (Move) null);
        else
            return legalMoves(logicBoard, previousMoves.peek());
    }

    @Override
    public void run() {
        getCurrentPlayer().intelligence.play();

        while (!stop) {

            if (currentMove != null) {
                awaitingPlayer = false;

                board.play(currentMove);

                previousMoves.push(currentMove);
                currentMove = null;

                System.out.println(evaluate(board.logicBoard, getCurrentPlayer()));

                getCurrentPlayer().intelligence.close();

                if (board.outcome() != empty)
                    break;

                turnCount++;
                getCurrentPlayer().intelligence.play();
            }

            try {
                Thread.sleep(1000 / targetedFrameRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (stop)
            return;

        boolean hasHuman = false;
        for (Player player : players)
            if (player.intelligence instanceof Human)
                hasHuman = true;

        if (hasHuman)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        if (!stop)
            onWin(board.outcome());
    }

    @Override
    public void terminate() {
        for (Player player : players)
            player.intelligence.terminate();
        super.terminate();
    }

    // evaluates logicBord regarding player
    public static double evaluate(LogicBoard logicBoard, Player player) {
        if (logicBoard.outcome() == player)
            return Double.POSITIVE_INFINITY;
        if (logicBoard.outcome() != empty)
            return Double.NEGATIVE_INFINITY;

        if (logicBoard instanceof FinalLogicBoard) {
            return 0;
        } else if (logicBoard instanceof TwoDimensionalLogicBoard) {
            double value = evaluateWinningLines(((TwoDimensionalLogicBoard) logicBoard).getLogicBoards(), player);
            return value;
        }

        return 0;
    }

    // basic evaluation of winning lines
    private static double evaluateWinningLines(LogicBoard[][] logicBoards, Player player) {
        double value = 0;

        int countPlayer;
        int countOpponent;
        double additional;
        int size = logicBoards.length;

        // evaluates columns
        for (int i = 0; i < size; i++) {
            countPlayer = 0;
            countOpponent = 0;
            additional = 0;
            for (int j = 0; j < size; j++) {
                if (logicBoards[i][j].outcome() == player)
                    countPlayer++;
                else if (logicBoards[i][j].outcome() != empty && logicBoards[i][j].outcome() != undecided)
                    countOpponent++;
                additional += ratio(evaluate(logicBoards[i][j], player));
                if (countOpponent > 0 && countPlayer > 0)
                    break;
            }
            if (countOpponent == 0 || countPlayer == 0) {
                value += additional;
            }
        }

        // evaluate rows
        for (int j = 0; j < size; j++) {
            countPlayer = 0;
            countOpponent = 0;
            additional = 0;
            for (int i = 0; i < size; i++) {
                if (logicBoards[i][j].outcome() == player)
                    countPlayer++;
                else if (logicBoards[i][j].outcome() != empty && logicBoards[i][j].outcome() != undecided)
                    countOpponent++;
                additional += ratio(evaluate(logicBoards[i][j], player));
                if (countOpponent > 0 && countPlayer > 0)
                    break;
            }
            if (countOpponent == 0 || countPlayer == 0)
                value += additional;
        }

        // evaluate diagonals
        countPlayer = 0;
        countOpponent = 0;
        additional = 0;
        for (int i = 0; i < size; i++) {
            int j = i;
            if (logicBoards[i][j].outcome() == player)
                countPlayer++;
            else if (logicBoards[i][j].outcome() != empty && logicBoards[i][j].outcome() != undecided)
                countOpponent++;
            additional += ratio(evaluate(logicBoards[i][j], player));
            if (countOpponent > 0 && countPlayer > 0)
                break;
        }
        if (countOpponent == 0 || countPlayer == 0)
            value += additional;

        countPlayer = 0;
        countOpponent = 0;
        additional = 0;
        for (int i = 0; i < size; i++) {
            int j = size - i - 1;
            if (logicBoards[i][j].outcome() == player)
                countPlayer++;
            else if (logicBoards[i][j].outcome() != empty && logicBoards[i][j].outcome() != undecided)
                countOpponent++;
            additional += ratio(evaluate(logicBoards[i][j], player));
            if (countOpponent > 0 && countPlayer > 0)
                break;
        }
        if (countOpponent == 0 || countPlayer == 0)
            value += additional;

        return value;
    }

    // gets ratio from value - main usage is to calculate average of +- infinity with other values
    private static double ratio(double value) {
        // basic homeomorphism from R to (-1, 1), extended with infinities
        if (value == Double.POSITIVE_INFINITY)
            return 1;
        else if (value == Double.NEGATIVE_INFINITY)
            return -1;
        else
            return 0.5 * value / (1 + Math.abs(value));
    }

}
