package display.screens;

import display.frame.DisplayComponent;
import display.frame.MainFrame;
import display.frame.MainPanel;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.buttons.NormalButton;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen {

    /*
    Game is effectively divided into different screens. Only one screen can be active at the time. Screens populate and
    depopulate MainPanel's displayComponents. Screens have almost identical structure due to laziness. Originally they
    were intended to have various images and interesting visual effects.
     */

    boolean active = false;
    private List<DisplayComponent> displayComponents = new ArrayList<>();
    private Screen previousScreen;  // keeps track of what screen to return to

    // loads all necessary components
    public abstract void load(MainFrame mainFrame);

    // unloads upon switching to another screen
    public void unload(MainFrame mainFrame) {
        for (DisplayComponent displayComponent : displayComponents)
            mainFrame.panel.removeDisplayComponent(displayComponent);
    }

    public void addDisplayComponent(DisplayComponent displayComponent, MainPanel panel) {
        if (!displayComponents.contains(displayComponent))
            displayComponents.add(displayComponent);
        panel.addDisplayComponent(displayComponent);
    }

    public void removeDisplayComponent(DisplayComponent displayComponent, MainPanel panel) {
        displayComponents.remove(displayComponent);
        removeDisplayComponentSilent(displayComponent, panel);
    }

    // removes display component only from panel (effectively isn't displayed) - not from screen
    private void removeDisplayComponentSilent(DisplayComponent displayComponent, MainPanel panel) {
        panel.removeDisplayComponent(displayComponent);
    }

    // back button should almost always be the same
    void addDefaultBackButton(MainFrame mainFrame) {
        NormalButton backButton = new NormalButton("Back", 4, new Dimension(20, 8)) {
            @Override
            public void clicked() {
                Controller.back();
            }
        };
        backButton.coordinates = new Coordinates(2, 2);
        addDisplayComponent(backButton, mainFrame.panel);
    }

    public void setPreviousScreen(Screen screen) {
        previousScreen = screen;
    }

    Screen getPreviousScreen() {
        return previousScreen;
    }

}
