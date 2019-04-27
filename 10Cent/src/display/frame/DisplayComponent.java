package display.frame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Comparator;

public interface DisplayComponent {

    Comparator<DisplayComponent> comparator = Comparator.comparingInt(DisplayComponent::getDisplayPriority);

    // is given point of origin
    void paint(Coordinates coordinates, Graphics g);

    // lower priority gets painted first (background)
    int getDisplayPriority();

    Coordinates getCoordinates();

    // following two methods should be overridden (both) if the object is interactive

    default boolean contains(Coordinates coordinates) {
        return false;
    }

    default void interact(Coordinates coordinates, MouseEvent mouseEvent) {
        assert false;  // isn't called if contains returns false
    }
}
