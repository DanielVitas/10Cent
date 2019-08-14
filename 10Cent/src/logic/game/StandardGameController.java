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
        return 0;
    }

    // evaluates logicBord regarding player
    public static double evaluate(LogicBoard logicBoard, Player player) {
        if (logicBoard.outcome() == undecided)
            return 0;
        else if (logicBoard.outcome() == player)
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

        int[] counter = new int[2];
        double[] additional = new double[1];
        int size = logicBoards.length;

        // evaluates columns
        for (int i = 0; i < size; i++) {
            resetCounters(counter, additional);
            for (int j = 0; j < size; j++)
                if (countOutcome(logicBoards, player, i, j, counter, additional))
                    break;
            if (counter[0] == 0 || counter[1] == 0) {
                value += additional[0]; // if line cannot be won by either player, it's value isn't added
            }
        }

        // evaluate rows
        for (int j = 0; j < size; j++) {
            resetCounters(counter, additional);
            for (int i = 0; i < size; i++)
                if (countOutcome(logicBoards, player, i, j, counter, additional))
                    break;
            if (counter[0] == 0 || counter[1] == 0)
                value += additional[0];
        }

        // evaluate diagonals
        resetCounters(counter, additional);
        for (int i = 0; i < size; i++)
            if (countOutcome(logicBoards, player, i, i, counter, additional))
                break;
        if (counter[0] == 0 || counter[1] == 0)
            value += additional[0];

        resetCounters(counter, additional);
        for (int i = 0; i < size; i++)
            if (countOutcome(logicBoards, player, i, size - i - 1, counter, additional))
                break;
        if (counter[0] == 0 || counter[1] == 0)
            value += additional[0];

        return value;
    }

    private static void resetCounters(int[] counter, double[] additional) {
        counter[0] = 0;
        counter[1] = 0;
        additional[0] = 0.;
    }

    // returns whether the loop should break
    private static boolean countOutcome(LogicBoard[][] logicBoards, Player player, int i, int j, int[] counter, double[] additional) {
        Player outcome = logicBoards[i][j].outcome();

        if (outcome == player)
            counter[0]++;
        else if (outcome == undecided) {
            // no one can win
            counter[0]++;
            counter[1]++;
        } else if (outcome != empty)
            counter[1]++;

        if (counter[0] > 0 && counter[1] > 0)
            return true; // this line cannot be won by either player

        additional[0] += ratio(evaluate(logicBoards[i][j], player));
        return false;
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
