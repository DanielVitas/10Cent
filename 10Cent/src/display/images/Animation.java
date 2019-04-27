package display.images;

import display.frame.DisplayComponent;
import display.frame.Coordinates;

import java.awt.*;

public class Animation extends Thread implements DisplayComponent {

    /*
    Stores image paths and delays. Thread continuously changes image with respective delays. Should always be
    sub-component.
     */

    private final static Coordinates coordinates = new Coordinates(0, 0);

    public Image image;
    private String[] imagePaths;
    private long[] delays;
    private boolean loop;

    // delays will be applied by module (the list can be shorter than imageNames - often singleton)
    public Animation(String[] imagePaths, long[] delays, boolean loop) {
        this.imagePaths = imagePaths;
        this.delays = delays;
        this.loop = loop;
    }

    // images will be displayed in alphabetical order
    public Animation(String directoryPath, long[] delays, boolean loop) {
        this(Images.getFilePaths(directoryPath), delays, loop);
    }

    public long length() {
        long length = 0;
        for (int i = 0; i < imagePaths.length; i++)
            length += delays[i % delays.length];
        return length;
    }


    // System sleeps for the length of animation
    public void sleep() {
        try {
            Thread.sleep(length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // should be overridden, is called after animation finishes
    protected void finished() {

    }

    @Override
    public void run() {
        while (loop)
            for (int i = 0; i < imagePaths.length; i++) {
                image = Images.get(imagePaths[i]);

                try {
                    Thread.sleep(delays[i % delays.length]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        finished();
    }

    @Override
    public void paint(Coordinates coordinates, Graphics g) {
        g.drawImage(image, (int) coordinates.getX(), (int) coordinates.getY(), null);
    }

    @Override
    public int getDisplayPriority() {
        return 0;  // has no meaning in animation
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;  // always (0, 0)
    }

}