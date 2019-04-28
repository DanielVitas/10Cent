package display.frame.misc;

import java.awt.*;
import java.awt.geom.Point2D;

public class Coordinates extends Point2D {

    /*
    Implementation of abstract class Point2D.
     */

    private double x, y;

    public Coordinates(double x, double y) {
        setLocation(x, y);
    }

    @Deprecated
    public Coordinates(Point point) {
        this(point.x, point.y);
    }

    public Coordinates add(double x, double y) {
        return new Coordinates(this.x + x, this.y + y);
    }

    public Coordinates add(Coordinates coordinates) {
        return new Coordinates(this.x + coordinates.x, this.y + coordinates.y);
    }

    public Coordinates flip() {
        return new Coordinates(-this.x, -this.y);
    }

    public Coordinates scale(Scale scale) {
        return new Coordinates(x * scale.horizontal, y * scale.vertical);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double v, double v1) {
        x = v;
        y = v1;
    }
}
