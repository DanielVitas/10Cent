package display.screens;

import display.frame.MainFrame;
import display.frame.Mouse;

public final class Controller {

    /*
    This class manages screens and basic interactions between them (switching).
     */

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
            Mouse.hovered = null;
        }
        screen.active = true;
        screen.load(mainFrame);
        Controller.screen = screen;
    }

    // sometimes you don't want to be able to back to previous screen - but rather to the one previous would back to
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
