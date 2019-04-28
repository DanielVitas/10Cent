package display.frame.misc;

public class Scale {

    public double horizontal, vertical;

    public Scale(double horizontal, double vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Scale multiply(Scale scale) {
        return new Scale(horizontal * scale.horizontal, vertical * scale.vertical);
    }

    public Scale multiply(float horizontal, float vertical) {
        return new Scale(this.horizontal * horizontal, this.vertical * vertical);
    }

}
