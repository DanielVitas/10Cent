package display.screens;

import audio.AudioPlayer;
import audio.MusicPlayer;
import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;
import settings.Settings;

import java.util.Set;


public class SettingsScreen extends Screen{

    public final static boolean windowed = Settings.windowedMode;

    @Override
    public void load(MainFrame mainFrame){
        NormalSlider globalVolume = new NormalSlider(new Dimension(20,10));
        globalVolume.coordinates = new Coordinates(10,30);
        globalVolume.setValue(Settings.globalVolume/100);
        addDisplayComponent(globalVolume, mainFrame.panel);

        NormalSlider soundVolume = new NormalSlider(new Dimension(20,10));
        soundVolume.coordinates = new Coordinates(10,50);
        soundVolume.setValue(Settings.soundVolume/100);
        addDisplayComponent(soundVolume, mainFrame.panel);

        NormalSlider musicVolume = new NormalSlider(new Dimension(20,10));
        musicVolume.coordinates = new Coordinates(10,70);
        musicVolume.setValue(Settings.musicVolume/100);
        addDisplayComponent(musicVolume, mainFrame.panel);

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
                Settings.globalVolume = globalVolume.getValue()*100;
                Settings.soundVolume = soundVolume.getValue()*100;
                Settings.musicVolume = musicVolume.getValue()*100;
                Settings.save();
                Controller.back(); }
        };
        saveButton.coordinates = new Coordinates(65,80);
        addDisplayComponent(saveButton, mainFrame.panel);

        NormalButton windowMode = new NormalButton(getWindowMode(), new Dimension(20, 10)) {
            @Override
            public void clicked() {
                Settings.windowedMode = !Settings.windowedMode;
                this.label.text=getWindowMode();
            }
        };
        windowMode.coordinates = new Coordinates(50,50);
        addDisplayComponent(windowMode, mainFrame.panel);

        NormalButton playEpic = new NormalButton("Dze se kupas", new Dimension(10,10)) {
            @Override
            public void clicked() {
                AudioPlayer.play(MusicPlayer.TEST);
            }
        };
        playEpic.coordinates = new Coordinates(80,10);
        addDisplayComponent(playEpic, mainFrame.panel);
    }

    private static String getWindowMode() {
        if(Settings.windowedMode)return "Windowed";
            return "Fullscreen";
    }

}
