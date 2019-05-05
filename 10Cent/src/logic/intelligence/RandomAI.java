package logic.intelligence;

import logic.boards.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAI extends Intelligence {

    public final static String NAME = "Random";
    private Random random;

    public RandomAI() {
        random = new Random();
    }

    @Override
    public void play() {
        List<Move> moves = new ArrayList<>(gameController.legalMoves());
        Move randomMove = moves.get(random.nextInt(moves.size()));
        randomMove.setPlayer(gameController.getCurrentPlayer());
        gameController.currentMove = randomMove;
    }

    @Override
    public void close() {

    }

}
