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
        screen.setPreviousScreen(Controller.screen);
        if (Controller.screen != null) {
            Controller.screen.active = false;
            Controller.screen.unload(mainFrame);
        }
        screen.active = true;
        screen.load(mainFrame);
        Controller.screen = screen;
    }

    public static void switchScreenWithoutBacking(Screen screen) {
        if (Controller.screen != null) {
            screen.setPreviousScreen(Controller.screen.getPreviousScreen());
            Controller.screen.unload(mainFrame);
        }
        screen.load(mainFrame);
        Controller.screen = screen;
    }

    public static void back() {
        if (screen.getPreviousScreen() == null)
            return;
        screen.active = false;
        screen.unload(mainFrame);
        screen.getPreviousScreen().active = true;
        screen.getPreviousScreen().load(mainFrame);
        screen = screen.getPreviousScreen();
    }



}
