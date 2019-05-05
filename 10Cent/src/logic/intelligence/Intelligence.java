package logic.intelligence;

import logic.boards.Move;
import logic.game.GameController;

public abstract class Intelligence {

    public GameController  gameController;

    public Intelligence() {

    }

    // sets Controller's current move
    public abstract void play();

    public abstract void close();

    public static Intelligence parseString(String string) {
        Intelligence intelligence = null;
        switch (string) {
            case Human.NAME:
                intelligence = new Human();
                break;
            case RandomAI.NAME:
                intelligence = new RandomAI();
                break;
            case MiniMax.NAME:
                intelligence = new MiniMax(10);
                break;
        }
        return intelligence;
    }

}
