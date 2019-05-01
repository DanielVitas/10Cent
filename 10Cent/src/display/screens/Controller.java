package display.screens;

import display.frame.MainFrame;

public final class Controller {

    private static MainFrame mainFrame;
    private static Screen screen;

    public static void install(MainFrame mainFrame) {
        Controller.mainFrame = mainFrame;
        switchScreen(new MainMenuScreen());
    }

    public static void switchScreen(Screen screen) {
        screen.previousScreen = Controller.screen;
        if (Controller.screen != null)
            Controller.screen.unload(mainFrame);
        screen.load(mainFrame);
        Controller.screen = screen;
    }

    public static void back() {
        if (screen.previousScreen == null)
            return;
        screen.unload(mainFrame);
        screen.previousScreen.load(mainFrame);
        screen = screen.previousScreen;
    }



}
