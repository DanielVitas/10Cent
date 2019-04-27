package display.frame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayObject implements DisplayComponent {

    public Coordinates coordinates;
    private List<DisplayComponent> subComponents = new ArrayList<>();  // used by default paint method
    protected List<Shape> hitBoxes = new ArrayList<>();  // hit-boxes should be empty if object is not interactive

    protected void addSubComponent(DisplayComponent subComponent) {
        subComponents.add(subComponent);
        subComponents.sort(DisplayComponent.comparator);
    }

    @Override
    public void paint(Coordinates coordinates, Graphics g) {
        for (DisplayComponent subComponent : subComponents)
            subComponent.paint(coordinates.add(subComponent.getCoordinates()), g);
    }

    @Override
    public int getDisplayPriority() {
        return 0;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    // non-interactive objects should have empty hit-boxes
    @Override
    public boolean contains(Coordinates coordinates) {
        for (Shape shape : hitBoxes)
            if (shape.contains(coordinates))
                return true;
        return false;
    }

}
