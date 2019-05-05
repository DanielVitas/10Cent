package display.widgets.dropdownMenu;

import display.frame.DisplayObject;
import display.frame.Mouse;
import display.frame.misc.Coordinates;
import display.frame.misc.Scale;

import java.awt.*;
import java.awt.event.MouseEvent;

import static display.frame.MainPanel.drawRectangle;

public abstract class DropdownMenu extends DisplayObject {

    protected int index = 0;
    protected String[] values;
    protected boolean dropeddown = false;
    public DisplayObject hoveredObject;
    protected DisplayObject displayedObject;
    protected DisplayObject[] otherObjects;

    public DropdownMenu(String[] values) {
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

    public int getIndex() {
        return index;
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
            if (displayedObject.contains(newCoordinates, scale, mouseEvent)) {
                if (!Mouse.pressed)
                    if (hoveredObject != displayedObject) {
                        if (hoveredObject != null)
                            hoveredObject.unhover(coordinates, scale, mouseEvent);
                        hoveredObject = displayedObject;
                        hoveredObject.hover(newCoordinates, scale, mouseEvent);
                    }
                return true;
            }
            for (DisplayObject otherObject : otherObjects) {
                newCoordinates = otherObject.coordinates.scale(scale).flip().add(coordinates);
                if (otherObject.contains(newCoordinates, scale, mouseEvent)) {
                    if (!Mouse.pressed)
                        if (hoveredObject != otherObject) {
                            if (hoveredObject != null)
                                hoveredObject.unhover(coordinates, scale, mouseEvent);
                            hoveredObject = otherObject;
                            otherObject.hover(newCoordinates, scale, mouseEvent);
                        }
                    return true;
                }
            }
        } else {
            Coordinates newCoordinates = displayedObject.coordinates.scale(scale).flip().add(coordinates);
            if (displayedObject.contains(newCoordinates, scale, mouseEvent)) {
                if (!Mouse.pressed)
                    if (hoveredObject != displayedObject) {
                        if (hoveredObject != null)
                            hoveredObject.unhover(coordinates, scale, mouseEvent);
                        hoveredObject = displayedObject;
                        hoveredObject.hover(newCoordinates, scale, mouseEvent);
                    }
                return true;
            }
        }
        if (hoveredObject != null)
            hoveredObject.unhover(coordinates, scale, mouseEvent);
        hoveredObject = null;
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
