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

}
