package display.widgets.buttons;

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

    private String directoryPath;  // contains animation directories
    private Dimension dimension;

    public Button(Dimension dimension, String directoryPath) {
        this.dimension = dimension;
        this.directoryPath = directoryPath;

        hitBoxes.add(new Rectangle(dimension.getAwtDimension()));

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

        path = Paths.get(directoryPath, "clicked").toString();
        Button button = this;
        animation = new Animation(path, new long[]{500}, false) {
            @Override
            protected void finished() {
                if (Mouse.hovered == button)
                    animateHovered();
                else
                    animateDefault();
            }
        };
        animation.dimension = dimension;
        addAnimation("clicked", animation);
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
        animate("clicked");
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