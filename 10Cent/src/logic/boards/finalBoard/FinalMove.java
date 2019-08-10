package logic.boards.finalBoard;

import logic.boards.Move;
import logic.players.Player;

public class FinalMove extends Move {

    public Player player;

    public FinalMove(Player player) {
        super();
        setNextMove(null);
        this.player = player;
    }

    @Override
    public Move clone() {
        return new FinalMove(player);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FinalMove))
            return false;
        return player == ((FinalMove) o).player;
    }

}
