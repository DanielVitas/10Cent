package display.screens;

import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.buttons.NormalButton;
import settings.Settings;


public class SettingsScreen extends Screen{

    public static boolean windowed;

    @Override
    public void load(MainFrame mainFrame){
        windowed = Settings.windowedMode;

        NormalButton backButton = new NormalButton("Back", new Dimension(20, 10)) {
            @Override
            public void clicked() {
                Controller.back();
            }
        };
        backButton.coordinates = new Coordinates(15, 80);
        addDisplayComponent(backButton, mainFrame.panel);

        NormalButton saveButton = new NormalButton("Save", new Dimension(20, 10)) {
            @Override
            public void clicked() {
                Settings.save();
                Controller.back(); }
        };
        saveButton.coordinates = new Coordinates(65,80);
        addDisplayComponent(saveButton, mainFrame.panel);

        NormalButton windowMode = new NormalButton(getWindowMode(), new Dimension(20, 10)) {
            @Override
            public void clicked() {
                if(!Settings.windowedMode && !windowed) {
                    windowed = false;
                } else {
                    windowed = true;
                }
                Settings.windowedMode = !Settings.windowedMode;
                this.label.text=getWindowMode();
            }
        };
        windowMode.coordinates = new Coordinates(50,50);
        addDisplayComponent(windowMode, mainFrame.panel);


    }

    private static String getWindowMode(){
        if(Settings.windowedMode)return "Windowed";
            return "Fullscreen";
    }
}
