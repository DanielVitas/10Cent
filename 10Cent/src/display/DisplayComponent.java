package display;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface DisplayComponent {

    int priority = 0;  // lower priority gets painted first (background)

    List<Shape> hitBoxes = new ArrayList<>();  // hitboxes should be empty if object is not interactive

    Comparator<DisplayComponent> comparator = Comparator.comparingInt(DisplayComponent::getPriority);

    void paint(Coordinates coordinates, Graphics g);  // is given point of origin

    default int getPriority() {
        return priority;
    }

    // should be overriden if necessary
    default boolean contains(Coordinates coordinates) {
        for (Shape shape : hitBoxes)
            if (shape.contains(coordinates))
                return true;
        return false;
    }

    // clicked does not do anything by default
    default void clicked(Coordinates coordinates) {

    }
}
