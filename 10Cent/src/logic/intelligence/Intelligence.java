package logic.intelligence;

import logic.game.GameController;
import logic.players.Player;

public abstract class Intelligence {

    /*
    Intelligence conducts player's move. Any player must have intelligence (even human).
     */

    public GameController  gameController;
    protected Player player;  // player that this intelligence should benefit (usually player on turn)

    public void setPlayer(Player player) {
        this.player = player;
    }

    // sets Controller's current move (does NOT play it directly!) - no lengthy calculations are allowed here
    public abstract void play();

    // called when the player turn ends
    public abstract void close();

    // called from GameController's terminate - is called when switching screens
    public abstract void terminate();

    // creates intelligence from given string and additional parameter for depth if required
    public static Intelligence parseIntelligence(String string, Integer... args) {
        Intelligence intelligence = null;
        switch (string) {
            case Human.NAME:
                intelligence = new Human();
                break;
            case RandomAI.NAME:
                intelligence = new RandomAI();
                break;
            case MiniMax.NAME:
                intelligence = new MiniMax(args[0]);
                break;
        }
        return intelligence;
    }

}
