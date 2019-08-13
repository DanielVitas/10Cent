package logic.game;

import logic.boards.LogicBoard;
import logic.boards.Move;
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

    private static double weight = 10.; // value greater than 0, determines ratio - greater the weight, lesser the ratio

    public StandardGameController(Player[] players) {
        super(players);
    }


    // gets set of legal moves depending on previous move and current logic board
    public static Set<Move> legalMoves(LogicBoard logicBoard, Move previousMove) {
        if (previousMove == null)
            return logicBoard.allMoves(empty);

        Stack<Move> deconstructedPreviousMove = previousMove.deconstruct();
        deconstructedPreviousMove.pop(); // pop's final move
        return logicBoard.legalMoves(empty, deconstructedPreviousMove);
    }

    @Override
    public void run() {
        currentPlayer().intelligence.play();

        while (!stop) {

            if (currentMove != null) {
                awaitingPlayer = false;

                board.play(currentMove);

                previousMoves.push(currentMove);
                currentMove = null;

                currentPlayer().intelligence.close();

                // games without human would play to fast for one to follow them
                if (!humanPlaying())
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                if (board.outcome() != empty)
                    break;

                turnCount++;
                currentPlayer().intelligence.play();
            }

            try {
                Thread.sleep(1000 / targetedFrameRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (stop)
            return;

        // waits a second, so human can see outcome of the board
        if (humanPlaying())
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        if (!stop)
            onWin(board.outcome());
    }

    // checks if there exists a player with human intelligence
    private boolean humanPlaying() {
        for (Player player : players)
            if (player.intelligence instanceof Human)
                return true;
        return false;
    }

    @Override
    public void terminate() {
        for (Player player : players)
            player.intelligence.terminate();
        super.terminate();
    }

    public static double basicEvaluate(LogicBoard logicBoard, Player player) {
        if (logicBoard.outcome() == player)
            return Double.POSITIVE_INFINITY;
        else if (logicBoard.outcome() != empty)
            return Double.NEGATIVE_INFINITY;
        else
            return 0;
    }

    // evaluates logicBord regarding player
    public static double evaluate(LogicBoard logicBoard, Player player) {
        if (logicBoard.outcome() == player)
            return Double.POSITIVE_INFINITY;
        else if (logicBoard.outcome() != empty)
            return Double.NEGATIVE_INFINITY;

        if (logicBoard instanceof TwoDimensionalLogicBoard)
            return evaluateWinningLines(((TwoDimensionalLogicBoard) logicBoard).getLogicBoards(), player);

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
                value += additional; // if line cannot be won by either player, it's value isn't added
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
            return value / (weight + Math.abs(value));
    }

}
