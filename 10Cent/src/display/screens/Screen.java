package display.screens;

import display.frame.DisplayComponent;
import display.frame.MainFrame;
import display.frame.MainPanel;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.buttons.NormalButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Screen {

    protected List<DisplayComponent> displayComponents = new ArrayList<>();
    private Screen previousScreen;

    // loads all necessary components
    public abstract void load(MainFrame mainFrame);

    public void unload(MainFrame mainFrame) {
        for (DisplayComponent displayComponent : displayComponents)
            mainFrame.panel.removeDisplayComponent(displayComponent);
    }

    public void addDisplayComponent(DisplayComponent displayComponent, MainPanel panel) {
        displayComponents.add(displayComponent);
        panel.addDisplayComponent(displayComponent);
    }

    public void removeDisplayComponent(DisplayComponent displayComponent, MainPanel panel) {
        displayComponents.remove(displayComponent);
        panel.removeDisplayComponent(displayComponent);
    }

    protected void addDefaultBackButton(MainFrame mainFrame) {
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

    public Screen getPreviousScreen() {
        return previousScreen;
    }

}
