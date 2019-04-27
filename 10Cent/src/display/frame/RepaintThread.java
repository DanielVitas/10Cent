package display.frame;

import javax.swing.*;

import static display.frame.MainFrame.targetedFramerate;

public class RepaintThread extends Thread {

    /*
    Thread, only meant to constantly call repaint, so the images are fluid.
     */

    private JPanel panel;

    public RepaintThread(JPanel panel) {
        this.panel = panel;
        setName("Repaint thread");
    }

    @Override
    public void run() {
        while (true) {
            panel.repaint();

            try {
                Thread.sleep(1000 / targetedFramerate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
