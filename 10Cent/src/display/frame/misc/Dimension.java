package display.frame.misc;

import java.awt.*;

public class Dimension {

    public double width, height;

    public Dimension(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Dimension(Image image) {
        this(image.getWidth(null), image.getHeight(null));
    }

    @Deprecated
    public java.awt.Dimension getAwtDimension() {
        return new java.awt.Dimension((int) width, (int) height);
    }

    public Dimension scale(Scale scale) {
        return new Dimension(width * scale.horizontal, height * scale.vertical);
    }

    // used when scaling from original dimension to new
    public Scale getScale(Dimension newDimension) {
        return new Scale(newDimension.width / width, newDimension.height / height);
    }

}
