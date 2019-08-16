package display.widgets.buttons;

import audio.AudioPlayer;
import audio.Sound;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Animation;
import display.images.Images;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.nio.file.Paths;

import static display.frame.MainPanel.drawEllipse;

public abstract class Waypoint extends Button {

    /*
    Waypoints are used to navigate through campaign screen. In essence they are buttons - only visually different.
     */

    private Active active;

    protected Waypoint(Active active, Dimension dimension) {
        super(dimension, Paths.get(Images.RESOURCES_PATH,"images", "buttons", "waypoint").toString());

        this.active = active;
        animateDefault();

        hitBoxes.add(new Ellipse2D.Double(0, 0, dimension.width, dimension.height));
    }

    @Override
    protected void installAnimations() {
        // waypoints have a lot more animations than other buttons
        super.installAnimations();

        Animation animation;
        String path;

        path = Paths.get(directoryPath, "default-locked").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("default-locked", animation);

        path = Paths.get(directoryPath, "default-next").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("default-next", animation);

        path = Paths.get(directoryPath, "hovered-next").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("hovered-next", animation);

        path = Paths.get(directoryPath, "pressed-next").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("pressed-next", animation);

        path = Paths.get(directoryPath, "unlock").toString();
        animation = new Animation(path, new long[]{500}, false) {
            @Override
            protected void finished() {
                animateDefault();
            }
        };
        animation.dimension = dimension;
        addAnimation("unlock", animation);
    }

    @Override
    public void animateDefault() {
        if (active == null)
            return;
        switch (active) {
            case UNLOCKED:
                animate("default");
                break;
            case NEXT:
                animate("default-next");
                break;
            case LOCKED:
                animate("default-locked");
                break;
        }
    }

    @Override
    public void animateHovered() {
        switch (active) {
            case UNLOCKED:
                animate("hovered");
                break;
            case NEXT:
                animate("hovered-next");
                break;
            case LOCKED:
                break;
        }
    }

    @Override
    public void animatePressed() {
        switch (active) {
            case UNLOCKED:
                animate("pressed");
                break;
            case NEXT:
                animate("pressed-next");
                break;
            case LOCKED:
                break;
        }
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        switch (active) {
            case UNLOCKED:
            case NEXT:
                AudioPlayer.play(Sound.BUTTON);
                break;
            case LOCKED:
                break;
        }
        super.click(coordinates, scale, mouseEvent);
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        super.paint(coordinates, scale, g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke((float) (0.3 * scale.average())));

        drawEllipse(coordinates, dimension.scale(scale), g);
    }

    @Override
    public void clicked() {
        switch (active) {
            case UNLOCKED:
            case NEXT:
                clickedUnlocked();
                break;
            case LOCKED:
                clickedLocked();
                break;
        }
    }

    protected abstract void clickedUnlocked();

    protected abstract void clickedLocked();

}
