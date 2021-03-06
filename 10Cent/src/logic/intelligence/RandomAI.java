package logic.intelligence;

import logic.boards.Move;
import logic.game.StandardGameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAI extends Intelligence {

    /*
    It chooses it's move randomly - each move has equal chance to be chosen.
     */

    public final static String NAME = "Random";
    private Random random;

    public RandomAI() {
        random = new Random();
    }

    @Override
    public void play() {
        List<Move> moves = new ArrayList<>(StandardGameController.legalMoves(gameController.board.logicBoard,
                gameController.previousMove()));  // gets all legal moves

        Move randomMove = moves.get(random.nextInt(moves.size()));  // picks a random move
        randomMove.setPlayer(gameController.currentPlayer());  // move has player set to empty by default
        gameController.currentMove = randomMove;
    }

    @Override
    public void close() {

    }

    @Override
    public void terminate() {

    }

}
