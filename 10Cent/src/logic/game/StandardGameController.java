package logic.game;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.intelligence.Human;
import logic.players.Player;

import java.util.Set;
import java.util.Stack;

import static display.frame.MainFrame.targetedFrameRate;
import static logic.boards.Board.empty;

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

    public static double evaluate(LogicBoard logicBoard, Player player) {
        if (logicBoard.outcome() == player)
            return Double.POSITIVE_INFINITY;
        if (logicBoard.outcome() != empty)
            return Double.NEGATIVE_INFINITY;
        return 0;
    }
}
