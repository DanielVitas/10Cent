package display.animations;

import display.Images;

import java.awt.image.BufferedImage;

import static display.MainFrame.targetedFramerate;

public class Animation extends Thread {

    public BufferedImage image;
    private String[] imageNames;
    private long[] delays;
    private boolean loop;

    // delays will be applied by module (the list can be shorter than imageNames - often singleton)
    public Animation(String[] imageNames, long[] delays, boolean loop) {
        this.imageNames = imageNames;
        this.delays = delays;
        this.loop = loop;
    }

    // images will be displayed in alphabetical order
    public Animation(String folderName, long[] delays, boolean loop) {
        this.imageNames = imageNames;
        this.delays = delays;
        this.loop = loop;
    }

    @Override
    public void run() {
        while (loop)
            for (int i = 0; i < imageNames.length; i++) {
                image = Images.get(imageNames[i]);

                try {
                    Thread.sleep(delays[i % imageNames.length]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

}
