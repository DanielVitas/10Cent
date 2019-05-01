package logic;

import logic.boards.Board;
import logic.boards.Move;
import logic.boards.exceptions.InvalidMoveException;
import logic.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static display.frame.MainFrame.targetedFramerate;
import static logic.boards.Board.empty;

public class GameController extends Thread {

    /*
    GameController should be created for every game separately.
     */

    private Player[] players;
    private Board board;
    private int turnCount = 0;

    private Stack<Move> previousMoves = new Stack<>();
    public Move currentMove;

    public GameController(Player[] players) {
        this.players = players;
    }

    @Override
    public void run() {
        players[turnCount].intelligence.play();

        while (true) {

            if (currentMove != null) {
                try {
                    board.play(currentMove);
                } catch (InvalidMoveException e) {
                    e.printStackTrace();
                }

                if (board.outcome != empty)
                    break;

                previousMoves.push(currentMove);
                currentMove = null;
                players[++turnCount].intelligence.play();
            }

            try {
                Thread.sleep(1000 / targetedFramerate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // handle winner
    }

}
