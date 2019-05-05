package logic.game;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.players.Player;
import sun.rmi.runtime.Log;

import java.util.Set;
import java.util.Stack;

import static display.frame.MainFrame.targetedFramerate;
import static logic.boards.Board.empty;

public abstract class StandardGameController extends GameController {

    public StandardGameController(Player[] players) {
        super(players);
    }


    public static Set<Move> legalMoves(LogicBoard logicBoard, Move previousMove) {
        if (previousMove==null)
            return logicBoard.allMoves(empty);
        Stack<Move> deconstructedPreviousMove = previousMove.deconstruct();
        deconstructedPreviousMove.pop(); // pop's final move
        return logicBoard.legalMoves(empty, deconstructedPreviousMove);
    }

    @Override
    public void run() {
        getCurrentPlayer().intelligence.play();

        while (true) {

            if (currentMove != null) {
                awaitingPlayer = false;

                try {
                    board.play(currentMove);
                } catch (InvalidMoveException e) {
                    e.printStackTrace();
                }

                previousMoves.push(currentMove);
                currentMove = null;

                getCurrentPlayer().intelligence.close();

                if (board.outcome() != empty)
                    break;

                turnCount++;
                getCurrentPlayer().intelligence.play();
            }

            try {
                Thread.sleep(1000 / targetedFramerate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        onWin(board.outcome());
    }

    public static double evaluate(LogicBoard logicBoard, Player player) {
        if (logicBoard.outcome() == player)
            return Double.POSITIVE_INFINITY;
        if (logicBoard.outcome() != empty)
            return Double.NEGATIVE_INFINITY;
        return 0;
    }
}
