package logic.players;

import display.frame.misc.Dimension;

public abstract class Player {

    public Player() {

    }

    public abstract Token newToken(Dimension dimension);

}
