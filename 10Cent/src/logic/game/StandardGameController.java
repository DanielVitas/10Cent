package logic.game;

import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.players.Player;

import java.util.Set;

import static display.frame.MainFrame.targetedFramerate;
import static logic.boards.Board.empty;

public class StandardGameController extends GameController {

    public StandardGameController(Player[] players) {
        super(players);
    }

    @Override
    public Set<Move> legalMoves() {
        if (previousMoves.peek() != null)
            return board.compactBoard.legalMoves(getCurrentPlayer(), previousMoves.peek());
        return board.compactBoard.allMoves(getCurrentPlayer());
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

                if (board.outcome() != empty)
                    break;

                previousMoves.push(currentMove);
                currentMove = null;
                turnCount++;
                getCurrentPlayer().intelligence.play();
            }

            try {
                Thread.sleep(1000 / targetedFramerate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
