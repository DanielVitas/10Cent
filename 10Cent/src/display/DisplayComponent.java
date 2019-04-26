package display;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface DisplayComponent {

    int priority = 0;  // lower priority gets painted first (background)

    List<DisplayComponent> subComponents = new ArrayList<>();  // often useful, only stores DisplayComponents
    List<Shape> hitBoxes = new ArrayList<>();  // hit-boxes should be empty if object is not interactive

    Comparator<DisplayComponent> comparator = Comparator.comparingInt(DisplayComponent::getPriority);

    // is given point of origin
    default void paint(Coordinates coordinates, Graphics g) {
        for (DisplayComponent displayComponent : subComponents)
            displayComponent.paint(coordinates, g);
    }

    default int getPriority() {
        return priority;
    }

    // should be overridden if necessary
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
