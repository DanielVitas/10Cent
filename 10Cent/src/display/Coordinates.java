package display;

import java.awt.geom.Point2D;

public class Coordinates extends Point2D {

    /*
    Implementation of abstract class Point2D.
     */

    double x, y;

    public Coordinates(double x, double y) {
        setLocation(x, y);
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
