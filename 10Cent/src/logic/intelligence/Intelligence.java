package logic.intelligence;

import logic.game.GameController;
import logic.players.Player;

public abstract class Intelligence {

    /*
    Intelligence conducts player's move. Any player must have intelligence (even human).
     */

    public GameController  gameController;
    protected Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    // sets Controller's current move (does NOT play it directly!)
    public abstract void play();

    // called when the player turn ends
    public abstract void close();

    // called from GameController's terminate - is called when switching screens
    public abstract void terminate();

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
