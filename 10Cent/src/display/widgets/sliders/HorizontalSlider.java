package display.widgets.sliders;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;

public abstract class HorizontalSlider extends Slider {

    /*
    Slider's node is moving only horizontally.
     */

    private Double value = 0.5;

    public HorizontalSlider(Dimension dimension) {
        super(dimension);
    }

    // doesn't move node
    private void changeValue(double deltaX) {
        value += deltaX;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        node.coordinates = new Coordinates(this.value * (dimension.width - node.dimension.width),0);
    }

    @Override
    public Coordinates slide(Coordinates deltaCoordinates) {
        double deltaX = deltaCoordinates.getX() / (dimension.width - node.dimension.width);
        if (deltaX == 0)
            return new Coordinates(0, 0);
        double change;
        if (value + deltaX <= 1 && value + deltaX >= 0)
            change = deltaX;
        else if (value + deltaX > 1)
            change = 1 - value;
        else
            change = -value;
        changeValue(change);
        return deltaCoordinates.scale(new Scale(change / deltaX, 0));
    }

}
