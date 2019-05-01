package display.frame.misc;

public class Scale {

    public double horizontal, vertical;
    public static Scale noScale = new Scale(1,1);

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

    public Scale inverse() {
        if (horizontal == 0 || vertical == 0)
            System.out.println("Warning: non-existing inverse of " + this);
        return new Scale(1 / horizontal, 1 / vertical);
    }

}
