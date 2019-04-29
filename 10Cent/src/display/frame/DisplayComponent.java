package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Scale;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Comparator;

public interface DisplayComponent {

    Comparator<DisplayComponent> COMPARATOR = Comparator.comparingInt(DisplayComponent::getDisplayPriority);

    // is given point of origin (scaled) and scale
    void paint(Coordinates coordinates, Scale scale, Graphics g);

    // lower priority gets painted first (background)
    int getDisplayPriority();

    Coordinates getCoordinates();

    // returns true if coordinates are contained in object
    boolean contains(Coordinates coordinates, Scale scale);

    // called when (unpressed) mouse first hovers the object
    void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when (unpressed) mouse doesn't hover the object anymore
    void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when (pressed) mouse is moved on last hovered object
    void drag(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when mouse is pressed (on currently hovered object)
    void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when mouse is released if object hovered on press isn't hovered on release
    void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when mouse is released only if the object was also hovered on press
    void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

}
