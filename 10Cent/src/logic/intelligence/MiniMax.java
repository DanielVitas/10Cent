package logic.intelligence;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.game.StandardGameController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniMax extends Intelligence{

    private int depth;

    public MiniMax(int depth) {
        this.depth = depth;
    }

    // the AI is currently recursive but we would want it to be iterative
    @Override
    public void play() {
        Move nextMove;
        if(gameController.previousMoves.empty()){
            nextMove = getMove(depth, gameController.board.logicBoard.clone(), null);
        } else {
            nextMove = getMove(depth, gameController.board.logicBoard.clone(), gameController.previousMoves.peek());
        }


        nextMove.setPlayer(gameController.getCurrentPlayer());
        gameController.currentMove = nextMove;
    }

    @Override
    public void close() {

    }
    private Move getMove(int depth, LogicBoard logicBoard, Move previousMove) {
        List<Integer> moveValues = new ArrayList<>();
        List<Move> moves = new ArrayList<>(StandardGameController.legalMoves(logicBoard, previousMove));
        for(Move move: moves) {
            moveValues.add(evaluateMove(depth - 1, logicBoard.clone(), move));
        }
        return moves.get(moveValues.indexOf(Collections.max(moveValues)));
    }

    private int evaluateMove(int depth, LogicBoard logicBoard, Move move) {
        logicBoard.play(move);
        List<Move> moves = new ArrayList<>(StandardGameController.legalMoves(logicBoard, move));
        if(depth==0 || moves.isEmpty()) {
            return StandardGameController.evaluate(logicBoard);
        } else {
        List<Integer> values = new ArrayList<>();
        for (Move nextMove: moves) {
            values.add(evaluateMove(depth - 1, logicBoard.clone(), nextMove));
        }
        if((this.depth - depth)%2==0) {
            return Collections.max(values);
        }
        return Collections.min(values);
        }

    }


}
