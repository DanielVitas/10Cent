package display;

import display.frame.MainFrame;
import display.screens.GameScreen;
import display.screens.Screen;

public final class Controller {

    private MainFrame mainFrame;
    private Screen screen;

    public Controller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        switchScreen(new GameScreen());
    }

    public void switchScreen(Screen screen) {
        screen.previousScreen = this.screen;
        if (this.screen != null)
            this.screen.unload(mainFrame);
        screen.load(mainFrame);
        this.screen = screen;
    }

    public void back() {
        if (screen.previousScreen == null)
            return;
        screen.unload(mainFrame);
        screen.previousScreen.load(mainFrame);
        screen = screen.previousScreen;
    }



}
