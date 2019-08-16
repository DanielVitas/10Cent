package display.widgets.dropdownMenu;

import display.frame.DisplayObject;
import display.frame.Mouse;
import display.frame.misc.Coordinates;
import display.frame.misc.Scale;

import java.awt.event.MouseEvent;

public abstract class DropdownMenu extends DisplayObject {

    /*
    DropdownMenu is used for multiple choices. It operates with strings, but can visually represented by any kind of
    DisplayObjects.
     */

    int index = 0;
    String[] values;
    boolean dropeddown = false;
    public DisplayObject hoveredObject;
    DisplayObject displayedObject;
    DisplayObject[] otherObjects;

    DropdownMenu(String[] values) {
        this.values = values;
    }

    public void setIndex(int index) {
        this.index = index;
        refresh();
    }

    public void setValue(String value) {
        for (int i = 0; i < values.length; i++)
            if (values[i].equals(value)) {
                setIndex(i);
                break;
            }
    }

    public String getValue() {
        return values[index];
    }

    public abstract void refresh();  // refreshes displayedObject

    public abstract void dropdown();

    public abstract void backup();

    @Override
    public boolean contains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (dropeddown) {
            Coordinates newCoordinates = displayedObject.coordinates.scale(scale).flip().add(coordinates);
            if (additionalContains(coordinates, scale, mouseEvent, newCoordinates, displayedObject))
                return true;
            for (DisplayObject otherObject : otherObjects) {
                newCoordinates = otherObject.coordinates.scale(scale).flip().add(coordinates);
                if (additionalContains(coordinates, scale, mouseEvent, newCoordinates, otherObject))
                    return true;
            }
        } else {
            Coordinates newCoordinates = displayedObject.coordinates.scale(scale).flip().add(coordinates);
            if (additionalContains(coordinates, scale, mouseEvent, newCoordinates, displayedObject))
                return true;
        }
        if (hoveredObject != null)
            hoveredObject.unhover(coordinates, scale, mouseEvent);
        hoveredObject = null;
        return false;
    }

    // this method's only purpose is to simplify code a bit - it hovers and unhovers appropriate objects
    private boolean additionalContains(Coordinates coordinates, Scale scale, MouseEvent mouseEvent, Coordinates newCoordinates, DisplayObject otherObject) {
        if (otherObject.contains(newCoordinates, scale, mouseEvent)) {
            if (!Mouse.pressed)
                if (hoveredObject != otherObject) {
                    if (hoveredObject != null)
                        hoveredObject.unhover(coordinates, scale, mouseEvent);
                    hoveredObject = otherObject;
                    hoveredObject.hover(newCoordinates, scale, mouseEvent);
                }
            return true;
        }
        return false;
    }

    @Override
    public void hover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject != null)
            hoveredObject.hover(coordinates, scale, mouseEvent);
    }

    @Override
    public void unhover(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject != null)
            hoveredObject.unhover(coordinates, scale, mouseEvent);
    }

    @Override
    public void press(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject == null)
            return;
        Coordinates newCoordinates = hoveredObject.coordinates.scale(scale).flip().add(coordinates);
        hoveredObject.press(newCoordinates, scale, mouseEvent);
    }

    @Override
    public void release(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject == null)
            return;
        Coordinates newCoordinates = hoveredObject.getCoordinates().scale(scale).flip().add(coordinates);
        hoveredObject.release(newCoordinates, scale, mouseEvent);
    }

    @Override
    public void click(Coordinates coordinates, Scale scale, MouseEvent mouseEvent) {
        if (hoveredObject == null)
            return;
        Coordinates newCoordinates = hoveredObject.getCoordinates().scale(scale).flip().add(coordinates);
        if (hoveredObject.contains(newCoordinates, scale, mouseEvent))
            hoveredObject.click(newCoordinates, scale, mouseEvent);
        else
            hoveredObject.release(newCoordinates, scale, mouseEvent);
    }

}
