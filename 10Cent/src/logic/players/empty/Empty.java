package logic.players.empty;

import display.frame.misc.Dimension;
import logic.players.Player;
import logic.players.Token;

public class Empty extends Player {

    public Empty() {
        super();
    }

    @Override
    public Token newToken(Dimension dimension) {
        return new EmptyToken(this, dimension);
    }

}
