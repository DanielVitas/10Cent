package display.images;

import display.frame.DisplayComponent;
import display.frame.DisplayObject;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.frame.misc.Scale;

import java.awt.*;

public class Animation extends DisplayObject implements Runnable {

    /*
    Stores image paths and delays. Thread continuously changes image with respective delays. Should always be
    sub-component (in respective DisplayObject).
     */

    public Dimension dimension;

    public Image image;
    private String[] imagePaths;
    private long[] delays;
    private boolean loop;
    private boolean stop;
    public boolean isStatic = false;

    private Thread thread;

    // delays will be applied by module (the list can be shorter than imageNames - often singleton)
    public Animation(String[] imagePaths, long[] delays, boolean loop) {
        this.imagePaths = imagePaths;
        this.delays = delays;
        this.loop = loop;
    }

    public void start() {
        if (thread == null) {
            stop = false;
            thread = new Thread(this);
            thread.start();
        }
    }

    // when force stopping finished() isn't called
    public void stop() {
        stop = true;
        thread = null;
    }

    // this will end thread even if it's sleeping
    public void interrupt() {
        thread.interrupt();
    }

    // images will be displayed in alphabetical order - names are usually of format "image*number*"
    public Animation(String directoryPath, long[] delays, boolean loop) {
        this(Images.getFilePaths(directoryPath), delays, loop);
    }

    // calculates animation length
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
    protected void finished() {}

    @Override
    public void run() {
        do {
            for (int i = 0; i < imagePaths.length; i++) {
                if (stop)
                    return;

                image = Images.get(imagePaths[i]);

                try {
                    Thread.sleep(delays[i % delays.length]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (loop);
        finished();
    }

    @Override
    public void paint(Coordinates coordinates, Scale scale, Graphics g) {
        Dimension scaledDimension;
        if (dimension == null)
            scaledDimension = (new Dimension(image)).scale(scale);
        else
            scaledDimension = dimension.scale(scale);
        g.drawImage(image, coordinates.getIntegerX(), coordinates.getIntegerY(), scaledDimension.getIntegerWidth(), scaledDimension.getIntegerHeight(), null);
    }

}
