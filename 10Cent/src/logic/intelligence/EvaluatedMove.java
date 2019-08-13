package logic.intelligence;

import logic.boards.Move;

public class EvaluatedMove {

    /*
    Stores move and it's rating.
     */

    public Move move;
    public double rating;

    public EvaluatedMove(Move move, double rating) {
        this.move = move;
        this.rating = rating;
    }

}
