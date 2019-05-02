package logic.boards.finalBoard;

import logic.boards.Move;
import logic.players.Player;

import static logic.boards.Board.empty;

public class FinalMove extends Move {

    public Player player;

    public FinalMove(Player player) {
        super();
        setNextMove(null);
        this.player = player;
    }

    @Override
    public Move clone() {
        FinalMove finalMove = new FinalMove(player);
        finalMove.setNextMove(getNextMove());
        return finalMove;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FinalMove))
            return false;
        return player == ((FinalMove) o).player;
    }

}
