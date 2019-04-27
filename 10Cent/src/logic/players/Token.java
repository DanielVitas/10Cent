package logic.players;

import display.frame.DisplayObject;
import display.images.Animation;

import java.nio.file.Paths;

public abstract class Token extends DisplayObject {

    public Player player;
    private String directoryPath;  // contains animation directories

    public Token(Player player, String directoryPath) {
        this.player = player;
        this.directoryPath = directoryPath;
    }

    public Animation animateDefault() {
        String path = Paths.get(directoryPath, "default").toString();
        Animation animation = new Animation(path, new long[]{500}, true);
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
        addSubComponent(animation);
        animation.start();
        return animation;
    }

}
