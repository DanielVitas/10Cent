package display.widgets.buttons;

import display.frame.DisplayObject;
import display.frame.Mouse;
import display.frame.misc.Coordinates;
import display.frame.misc.Scale;

import java.awt.event.MouseEvent;

public abstract class ToggleSwitch extends DisplayObject {

    /*
    Group of DisplayObjects of witch only one can be selected at the time.
     */

    private DisplayObject[] displayObjects;
    private DisplayObject hoveredObject;
    protected DisplayObject selectedObject;

    public ToggleSwitch(DisplayObject[] displayObjects) {
        this.displayObjects = displayObjects;
        for (DisplayObject displayObject : displayObjects)
            addSubComponent(displayObject);
    }

    public DisplayObject getSelected() {
        return selectedObject;
    }

    // should be overridden for visual effect
    public void select(DisplayObject displayObject) {
        if (selectedObject != null)
            deselect(coordinates);
        selectedObject = displayObject;
    }

    public void deselect(Coordinates coordinates) {
        selectedObject = null;
    }

    @Override
    public boolean contains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        for (DisplayObject otherObject : displayObjects) {
            Coordinates newCoordinates = otherObject.coordinates.scale(scale).flip().add(coordinates);
            if (otherObject.contains(newCoordinates, scale, mouseEvent)) {
                if (!Mouse.pressed)
                    if (hoveredObject != otherObject) {
                        if (hoveredObject != null && hoveredObject != selectedObject)
                            hoveredObject.unhover(coordinates, scale, mouseEvent);
                        hoveredObject = otherObject;
                        if (hoveredObject != selectedObject)
                            otherObject.hover(newCoordinates, scale, mouseEvent);
                    }
                return true;
            }
        }
        if (hoveredObject != null && hoveredObject != selectedObject)
            hoveredObject.unhover(coordinates, scale, mouseEvent);
        hoveredObject = null;
        return false;
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject != null && hoveredObject != selectedObject)
            hoveredObject.hover(coordinates, scale, mouseEvent);
    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject != null && hoveredObject != selectedObject)
            hoveredObject.unhover(coordinates, scale, mouseEvent);
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject == null || hoveredObject == selectedObject)
            return;
        Coordinates newCoordinates = hoveredObject.coordinates.scale(scale).flip().add(coordinates);
        hoveredObject.press(newCoordinates, scale, mouseEvent);
    }

    @Override
    public void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject == null || hoveredObject == selectedObject)
            return;
        Coordinates newCoordinates = hoveredObject.getCoordinates().scale(scale).flip().add(coordinates);
        hoveredObject.release(newCoordinates, scale, mouseEvent);
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject == null || hoveredObject == selectedObject)
            return;
        Coordinates newCoordinates = hoveredObject.getCoordinates().scale(scale).flip().add(coordinates);
        if (hoveredObject.contains(newCoordinates, scale, mouseEvent)) {
            hoveredObject.click(newCoordinates, scale, mouseEvent);
            select(hoveredObject);
        } else {
            hoveredObject.release(newCoordinates, scale, mouseEvent);
        }
    }

}
