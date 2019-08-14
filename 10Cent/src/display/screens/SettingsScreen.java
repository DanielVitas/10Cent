package display.screens;

import audio.AudioPlayer;
import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.label.Align;
import display.widgets.label.Label;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;
import settings.Settings;

import java.awt.*;


public class SettingsScreen extends Screen{

    /*
    Screen for changing settings.
     */

    private static Font font = new Font(Label.DEFAULT_FONT_STYLE, Font.PLAIN, 3);

    // used when backing without saving
    private double globalVolumeOriginal;
    private double soundVolumeOriginal;
    private double musicVolumeOriginal;

    private void revertOriginal() {
        Settings.globalVolume = globalVolumeOriginal;
        Settings.soundVolume = soundVolumeOriginal;
        Settings.musicVolume = musicVolumeOriginal;

        AudioPlayer.applyVolume();
    }

    private void setOriginal() {
        globalVolumeOriginal = Settings.globalVolume;
        soundVolumeOriginal = Settings.soundVolume;
        musicVolumeOriginal = Settings.musicVolume;
    }

    @Override
    public void load(MainFrame mainFrame){
        setOriginal();

        Label globalVolumeLabel = new Label("Master Volume", font, Color.BLACK, new Dimension(25,8), Align.LEFT);
        globalVolumeLabel.coordinates = new Coordinates(5,16);
        addDisplayComponent(globalVolumeLabel, mainFrame.panel);

        Label soundVolumeLabel = new Label("Sound Volume", font, Color.BLACK, new Dimension(25,8), Align.LEFT);
        soundVolumeLabel.coordinates = new Coordinates(5,26);
        addDisplayComponent(soundVolumeLabel, mainFrame.panel);

        Label musicVolumeLabel = new Label("Music Volume", font, Color.BLACK, new Dimension(25,8), Align.LEFT);
        musicVolumeLabel.coordinates = new Coordinates(5,36);
        addDisplayComponent(musicVolumeLabel, mainFrame.panel);

        Label fullscreenLabel = new Label("Screen Mode", font, Color.BLACK, new Dimension(25,8), Align.LEFT);
        fullscreenLabel.coordinates = new Coordinates(5,46);
        addDisplayComponent(fullscreenLabel, mainFrame.panel);

        NormalSlider globalVolume = new NormalSlider(new Dimension(40,1.5)) {
            @Override
            public Coordinates slide(Coordinates deltaCoordinates){
                Settings.globalVolume = getValue();
                AudioPlayer.applyVolume();
                return super.slide(deltaCoordinates);
            }
        };
        globalVolume.coordinates = new Coordinates(30,19);
        globalVolume.setValue(Settings.globalVolume);
        addDisplayComponent(globalVolume, mainFrame.panel);

        NormalSlider soundVolume = new NormalSlider(new Dimension(40,1.5)) {
            @Override
            public Coordinates slide(Coordinates deltaCoordinates) {
                Settings.soundVolume = getValue();
                AudioPlayer.applyVolume();
                return super.slide(deltaCoordinates);
            }
        };
        soundVolume.coordinates = new Coordinates(30,29);
        soundVolume.setValue(Settings.soundVolume);
        addDisplayComponent(soundVolume, mainFrame.panel);

        NormalSlider musicVolume = new NormalSlider(new Dimension(40,1.5)) {
            @Override
            public Coordinates slide(Coordinates deltaCoordinates) {
                Settings.musicVolume = getValue();
                AudioPlayer.applyVolume();
                return super.slide(deltaCoordinates);
            }
        };
        musicVolume.coordinates = new Coordinates(30,39);
        musicVolume.setValue(Settings.musicVolume);
        addDisplayComponent(musicVolume, mainFrame.panel);

        NormalButton saveButton = new NormalButton("Save", 5, new Dimension(20, 8)) {
            @Override
            public void clicked() {
                Settings.save();
                setOriginal();
                Controller.back();
            }
        };
        saveButton.coordinates = new Coordinates(78,90);
        addDisplayComponent(saveButton, mainFrame.panel);

        NormalButton resetButton = new NormalButton("Defaults", 5, new Dimension(30, 8)) {
            @Override
            public void clicked() {
                Settings.defaults();
                Settings.save();
                setOriginal();
                Controller.back();
            }
        };
        resetButton.coordinates = new Coordinates(46,90);
        addDisplayComponent(resetButton, mainFrame.panel);

        NormalButton windowMode = new NormalButton(getWindowMode(), 5, new Dimension(40, 8)) {
            @Override
            public void clicked() {
                Settings.windowedMode = !Settings.windowedMode;
                this.label.text = getWindowMode();
            }
        };
        windowMode.coordinates = new Coordinates(30,46);
        addDisplayComponent(windowMode, mainFrame.panel);

        addDefaultBackButton(mainFrame);
    }

    private static String getWindowMode() {
        return Settings.windowedMode ? "Windowed" : "Fullscreen";
    }

    @Override
    public void unload(MainFrame mainFrame) {
        super.unload(mainFrame);
        revertOriginal(); // any way it unloads, original sound setting should be applied (those change when saving)
    }

}
