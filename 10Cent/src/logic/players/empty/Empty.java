package logic.players.empty;

import logic.players.Player;
import logic.players.Token;

public class Empty extends Player {

    public Empty() {
        super();
    }

    @Override
    public Token newToken() {
        return new EmptyToken(this);
    }

}
