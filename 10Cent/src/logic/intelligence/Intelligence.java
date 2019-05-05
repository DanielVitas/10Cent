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

}
