package logic.players.cross;

import display.frame.misc.Dimension;
import logic.players.Player;
import logic.players.Token;

public class Cross extends Player {

    public Cross() {
        super();
    }

    @Override
    public Token newToken(Dimension dimension) {
        return new CrossToken(this, dimension);
    }
}
