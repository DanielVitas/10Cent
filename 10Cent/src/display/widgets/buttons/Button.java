package display.widgets.buttons;

import audio.AudioPlayer;
import audio.SoundPlayer;
import display.frame.DisplayComponent;
import display.frame.Mouse;
import display.frame.misc.Coordinates;
import display.frame.DisplayObject;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

public abstract class Button extends DisplayObject {

    protected String directoryPath;  // contains animation directories
    protected Dimension dimension;

    public Button(Dimension dimension, String directoryPath) {
        this.dimension = dimension;
        this.directoryPath = directoryPath;

        installAnimations();
        animateDefault();
    }

    public abstract void clicked();

    protected void installAnimations() {
        Animation animation;
        String path;

        path = Paths.get(directoryPath, "default").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("default", animation);

        path = Paths.get(directoryPath, "hovered").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("hovered", animation);

        path = Paths.get(directoryPath, "pressed").toString();
        animation = new Animation(path, new long[]{500}, true);
        animation.dimension = dimension;
        addAnimation("pressed", animation);
    }

    public void animateDefault() {
        animate("default");
    }

    public void animateHovered() {
        animate("hovered");
    }

    public void animatePressed() {
        animate("pressed");
    }

    public void animateClicked() {
        if (Mouse.hovered == this)
            animateHovered();
        else
            animateDefault();
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateHovered();
    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateDefault();
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animatePressed();
    }

    @Override
    public void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateDefault();
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        animateClicked();
        clicked();
    }

}
