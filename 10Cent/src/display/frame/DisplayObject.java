package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Scale;
import display.images.Animation;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayObject implements DisplayComponent {

    public Coordinates coordinates = new Coordinates(0, 0);
    public int displayPriority = 0;
    private List<DisplayComponent> subComponents = new ArrayList<>();  // used by default paint method
    protected List<Shape> hitBoxes = new ArrayList<>();  // hit-boxes should be empty if object is not interactive

    // the following is used for objects with a single main animation
    private Animation currentAnimation;
    private Map<String, Animation> animations = new HashMap<>();

    protected void addSubComponent(DisplayComponent subComponent) {
        subComponents.add(subComponent);
        subComponents.sort(DisplayComponent.COMPARATOR);
    }

    protected void removeSubComponent(DisplayComponent subComponent) {
        subComponents.remove(subComponent);
    }

    public void addAnimation(String name, Animation animation) {
        animations.put(name, animation);
    }

    public void animate(String name) {
        if (currentAnimation != null)
            removeSubComponent(currentAnimation);
        Animation animation = animations.get(name);
        if (animation != null) {
            currentAnimation = animation;
            addSubComponent(animation);
            animation.start();
        } else {
            System.out.println("Animation missing: " + name + " in object " + this);
        }
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        for (DisplayComponent subComponent : subComponents)
            subComponent.paint(coordinates.add(subComponent.getCoordinates().scale(scale)), scale, g);
    }

    @Override
    public int getDisplayPriority() {
        return displayPriority;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    // non-interactive objects should have empty hit-boxes
    @Override
    public boolean contains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        Coordinates descaledCoordinates = coordinates.scale(scale.inverse());
        for (Shape shape : hitBoxes)
            if (shape.contains(descaledCoordinates))
                return true;
        return false;
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {

    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {

    }

    @Override
    public void drag(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {

    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {

    }

    @Override
    public void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {

    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {

    }

}
