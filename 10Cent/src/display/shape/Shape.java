package display.shape;

public class Shape {

    private float x, y;

    public Shape(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float[] getCoordinates() {
        return new float[]{x, y};
    }

}
