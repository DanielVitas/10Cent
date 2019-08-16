package logic.boards.finalBoard;

import logic.boards.Move;
import logic.players.Player;

public class FinalMove extends Move {

    public Player player;

    FinalMove(Player player) {
        super();
        setNextMove(null);
        this.player = player;
    }

    @Override
    public Move clone() {
        return new FinalMove(player);
    }

}
