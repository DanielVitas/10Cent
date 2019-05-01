package logic.players;

import display.frame.misc.Dimension;
import logic.intelligence.Intelligence;

public abstract class Player {

    public Intelligence intelligence;

    public Player() {
        //this.intelligence = intelligence;
    }

    public abstract Token newToken(Dimension dimension);

}
