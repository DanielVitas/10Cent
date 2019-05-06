package logic.intelligence;

import logic.boards.LogicBoard;
import logic.boards.Move;
import logic.game.StandardGameController;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniMax extends Intelligence{

    public final static String NAME = "Minimax";

    private int depth;

    public MiniMax(int depth) {
        this.depth = depth;
    }

    // the AI is currently recursive but we would want it to be iterative
    @Override
    public void play() {
        Move nextMove;
        if(gameController.previousMoves.empty()){
            nextMove = getMove(gameController.board.logicBoard.clone(), null);
        } else {
            nextMove = getMove(gameController.board.logicBoard.clone(), gameController.previousMoves.peek());
        }


        nextMove.setPlayer(gameController.getCurrentPlayer());
        gameController.currentMove = nextMove;
    }

    private Move getMove(LogicBoard logicBoard, Move previousMove) {
        List<Move> moves = new ArrayList<>(StandardGameController.legalMoves(logicBoard, previousMove));
        Double bestDouble = Double.NEGATIVE_INFINITY;
        Move bestMove = moves.get(0);

        for(Move move: moves) {
            LogicBoard newLogicBoard = logicBoard.clone();
            move.setPlayer(gameController.getPlayerOnTurnCount(0));
            newLogicBoard.play(move);
            Double evaluatedMove = evaluateMove(depth - 1, newLogicBoard, move);
            if(evaluatedMove > bestDouble) {
                bestDouble = evaluatedMove;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private double evaluateMove(int depth, LogicBoard logicBoard, Move previousMove) {
        List<Move> moves = new ArrayList<>(StandardGameController.legalMoves(logicBoard, previousMove));

        if(depth==0 || moves.isEmpty()) {
            return StandardGameController.evaluate(logicBoard, gameController.getCurrentPlayer());
        } else {
            List<Double> values = new ArrayList<>();

            for (Move nextMove: moves) {
                nextMove.setPlayer(gameController.getPlayerOnTurnCount(this.depth - depth));
                LogicBoard newLogicBoard = logicBoard.clone();
                newLogicBoard.play(nextMove);
                values.add(evaluateMove(depth - 1, newLogicBoard, nextMove));
            }

            if((this.depth - depth)%2==0) {
                return Collections.max(values);
            }
            return Collections.min(values);
        }

    }

    @Override
    public void close() {

    }

    @Override
    public void terminate() {

    }

}
