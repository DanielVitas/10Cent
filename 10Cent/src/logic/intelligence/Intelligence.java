package logic.intelligence;

import logic.game.GameController;

public abstract class Intelligence {

    /*
    Intelligence conducts player's move. Any player must have intelligence (even human).
     */

    public GameController  gameController;

    public Intelligence() {

    }

    // sets Controller's current move
    public abstract void play();

    public abstract void close();

    public abstract void terminate();

    public static Intelligence parseIntelligence(String string, Integer... depths) {
        Intelligence intelligence = null;
        switch (string) {
            case Human.NAME:
                intelligence = new Human();
                break;
            case RandomAI.NAME:
                intelligence = new RandomAI();
                break;
            case MiniMax.NAME:
                intelligence = new MiniMax(depths[0]);
                break;
        }
        return intelligence;
    }

}
