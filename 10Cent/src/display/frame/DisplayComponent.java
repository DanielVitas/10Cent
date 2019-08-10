package display.frame;

import display.frame.misc.Coordinates;
import display.frame.misc.Scale;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Comparator;

public interface DisplayComponent {

    /*
    Every object that is displayed should implement this interface. It has many functions used by MainPanel and other
    DisplayComponents.
     */

    // comparator is used when sorting DisplayComponents in respective list in MainPanel
    Comparator<DisplayComponent> COMPARATOR = Comparator.comparingInt(DisplayComponent::getDisplayPriority);

    // paint is given point of origin (scaled), scale (at witch the object is viewed) and passed Graphics
    void paint(Coordinates coordinates, Scale scale, Graphics g);

    // lower priority gets painted first - is used in Comparator
    int getDisplayPriority();

    // returns relative coordinates
    Coordinates getCoordinates();

    // returns true if coordinates are contained in object
    boolean contains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when (unpressed) mouse first hovers the object
    void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when (unpressed) mouse doesn't hover the object anymore (only first time)
    void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when (pressed) mouse is moved on currently hovered object
    void drag(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when mouse is pressed (on currently hovered object)
    void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when mouse is released, if object hovered on press isn't hovered on release
    void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

    // called when mouse is released, if the object was also hovered on press
    void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent);

}
