package logic.players.cross;

import logic.players.Player;
import logic.players.Token;

public class Cross extends Player {

    public Cross() {
        super();
    }

    @Override
    public Token newToken() {
        return new CrossToken(this);
    }
}
