package display.screens;

import display.frame.DisplayComponent;
import display.frame.MainFrame;
import display.frame.MainPanel;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen {

    protected List<DisplayComponent> displayComponents = new ArrayList<>();
    public Screen previousScreen;

    public Screen() {

    }

    // loads all necessary components
    public abstract void load(MainFrame mainFrame);

    public void unload(MainFrame mainFrame) {
        for (DisplayComponent displayComponent : displayComponents)
            mainFrame.panel.removeDisplayComponent(displayComponent);
    }

    protected void addDisplayComponent(DisplayComponent displayComponent, MainPanel panel) {
        displayComponents.add(displayComponent);
        panel.addDisplayComponent(displayComponent);
    }

}
