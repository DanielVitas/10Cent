package display;

import javax.swing.*;

import static display.MainFrame.targetedFramerate;

public class RepaintThread extends Thread {

    private MainPanel panel;

    public RepaintThread(MainPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            panel.manualRepaint();

            try {
                Thread.sleep(1000 / targetedFramerate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
