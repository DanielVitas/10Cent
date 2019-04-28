package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Scale;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Comparator;

public interface DisplayComponent {

    Comparator<DisplayComponent> comparator = Comparator.comparingInt(DisplayComponent::getDisplayPriority);

    // is given point of origin (scaled) and scale
    void paint(Coordinates coordinates, Scale scale, Graphics g);

    // lower priority gets painted first (background)
    int getDisplayPriority();

    Coordinates getCoordinates();

    // following two methods should be overridden (both) if the object is interactive

    default boolean contains(Coordinates coordinates, Scale scale) {
        return false;
    }

    // coordinates are relative to the clicked object's origin
    default void clicked(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        assert false;  // isn't called if contains returns false
    }
}
