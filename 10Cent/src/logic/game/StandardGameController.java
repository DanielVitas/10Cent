package logic.game;

import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.players.Player;

import java.util.Set;
import java.util.Stack;

import static display.frame.MainFrame.targetedFramerate;
import static logic.boards.Board.empty;

public abstract class StandardGameController extends GameController {

    public StandardGameController(Player[] players) {
        super(players);
    }

    @Override
    public Set<Move> legalMoves() {
        if (previousMoves.empty())
            return board.logicBoard.allMoves(empty);
        Stack<Move> deconstructedPreviousMove = previousMoves.peek().deconstruct();
        deconstructedPreviousMove.pop(); // pop's final move
        return board.logicBoard.legalMoves(empty, deconstructedPreviousMove);
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

}
