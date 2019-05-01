package display.widgets.sliders;

import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;
import display.images.Images;
import display.widgets.sliders.nodes.Node;
import display.widgets.sliders.nodes.NormalNode;

import java.nio.file.Paths;

public abstract class HorizontalSlider extends Slider {

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


    @Override
    public Coordinates slide(Coordinates deltaCoordinates) {
        double deltaX = deltaCoordinates.getX() / dimension.width;
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

    public void setValue(double value) {
        this.value = value;
        node.coordinates = new Coordinates(this.value * dimension.width,0);
    }

}