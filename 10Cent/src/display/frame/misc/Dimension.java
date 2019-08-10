package display.frame.misc;

import java.awt.*;

public class Dimension {

    /*
    Dimension contains width, height and some basic operations.
     */

    public double width, height;

    public Dimension(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Dimension(Image image) {
        this(image.getWidth(null), image.getHeight(null));
    }

    public int getIntegerWidth() {
        return (int) (width + 0.5);
    }

    public int getIntegerHeight() {
        return (int) (height + 0.5);
    }

    public java.awt.Dimension getAwtDimension() {
        return new java.awt.Dimension(getIntegerWidth(), getIntegerHeight());
    }

    public Dimension scale(Scale scale) {
        return new Dimension(width * scale.horizontal, height * scale.vertical);
    }

    // used when scaling from original dimension to new
    public Scale getScale(Dimension newDimension) {
        return new Scale(newDimension.width / width, newDimension.height / height);
    }

}
