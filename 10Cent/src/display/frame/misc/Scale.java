package display.frame.misc;

public class Scale {

    /*
    Scale contains horizontal scale, vertical scale and some basic operations.
     */

    public double horizontal, vertical;
    public static Scale noScale = new Scale(1,1);

    public Scale(double horizontal, double vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    // calculates inverse scale - throws exception if scale = (0, 0)
    public Scale inverse() {
        return new Scale(1 / horizontal, 1 / vertical);
    }

    // calculates average scale - used when scaling brush size when painting
    public double average() {
        return Math.sqrt(horizontal * horizontal + vertical * vertical);
    }

}
