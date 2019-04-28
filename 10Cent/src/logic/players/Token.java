package logic.players;

import display.frame.DisplayObject;
import display.frame.misc.Dimension;
import display.images.Animation;

import java.nio.file.Paths;

public abstract class Token extends DisplayObject {

    public Player player;
    private String directoryPath;  // contains animation directories
    protected Dimension dimension;

    public Token(Player player, Dimension dimension, String directoryPath) {
        this.player = player;
        this.directoryPath = directoryPath;
        this.dimension = dimension;
    }

    public Animation animateDefault() {
        String path = Paths.get(directoryPath, "default").toString();
        Animation animation = new Animation(path, new long[]{500}, true) {
            @Override
            protected void finished() {
                removeSubComponent(this);
            }
        };
        animation.dimension = dimension;
        addSubComponent(animation);
        animation.start();
        return animation;
    }

    public Animation animatePlace() {
        String path = Paths.get(directoryPath, "place").toString();
        Animation animation = new Animation(path, new long[]{500}, false) {
            @Override
            protected void finished() {
                animateDefault();
            }
        };
        animation.dimension = dimension;
        addSubComponent(animation);
        animation.start();
        return animation;
    }

}
