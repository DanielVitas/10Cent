package display.widgets.sliders.nodes;

import display.frame.DisplayObject;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.widgets.sliders.Slider;

import java.awt.event.MouseEvent;

public abstract class Node extends DisplayObject {

    /*
    Node is part of slider - it can take any shape. Basic behaviour has already been defined.
     */

    private Slider slider;
    public Dimension dimension;

    // coordinates originally grabbed by mouse
    private Coordinates grabbedCoordinates;

    public Node(Slider slider) {
        this.slider = slider;
    }

    @Override
    public void drag(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        double dx = coordinates.getX() - grabbedCoordinates.getX();
        double dy = coordinates.getY() - grabbedCoordinates.getY();
        Coordinates deltaCoordinates = new Coordinates(dx, dy);
        Coordinates coordinateChange = slider.slide(deltaCoordinates);
        this.coordinates = this.coordinates.add(coordinateChange);
        grabbedCoordinates = grabbedCoordinates.add(coordinateChange);
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        grabbedCoordinates = coordinates;
    }

}
