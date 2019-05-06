package logic.intelligence;

import logic.boards.Move;
import logic.game.StandardGameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomAI extends Intelligence {

    public final static String NAME = "Random";
    private Random random;

    public RandomAI() {
        random = new Random();
    }

    @Override
    public void play() {
        List<Move> moves = new ArrayList<>(StandardGameController.legalMoves(gameController.board.logicBoard, gameController.previousMoves));

        Move randomMove = moves.get(random.nextInt(moves.size()));
        randomMove.setPlayer(gameController.getCurrentPlayer());
        gameController.currentMove = randomMove;
    }

    @Override
    public void close() {

    }

    @Override
    public void terminate() {

    }

}
