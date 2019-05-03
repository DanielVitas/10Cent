package display.screens;

import audio.AudioPlayer;
import audio.MusicPlayer;
import display.frame.MainFrame;
import display.frame.misc.Coordinates;
import display.frame.misc.Dimension;
import display.widgets.Label;
import display.widgets.buttons.NormalButton;
import display.widgets.sliders.NormalSlider;
import settings.Settings;

import java.awt.*;
import java.util.Set;


public class SettingsScreen extends Screen{

    private static double globalVolumeV = Settings.globalVolume;
    private static double musicVolumeV = Settings.musicVolume;
    private static double soundVolumeV = Settings.soundVolume;

    private static Font font = new Font(Label.DEFAUZLT_FONT_STYLE, Font.PLAIN,  4);

    @Override
    public void load(MainFrame mainFrame){

        Label globalVolumeLabel = new Label("Master Volume", font, Color.BLACK, new Dimension(30, 10));
        globalVolumeLabel.coordinates = new Coordinates(0,5);
        addDisplayComponent(globalVolumeLabel, mainFrame.panel);

        Label soundVolumeLabel = new Label("Sound Volume", font, Color.BLACK, new Dimension(30, 10));
        soundVolumeLabel.coordinates = new Coordinates(0,20);
        addDisplayComponent(soundVolumeLabel, mainFrame.panel);

        Label musicVolumeLabel = new Label("Music Volume", font, Color.BLACK, new Dimension(30, 10));
        musicVolumeLabel.coordinates = new Coordinates(0,35);
        addDisplayComponent(musicVolumeLabel, mainFrame.panel);

        Label fullscreenLabel = new Label("Screen Mode", font, Color.BLACK, new Dimension(30, 10));
        fullscreenLabel.coordinates = new Coordinates(0,50);
        addDisplayComponent(fullscreenLabel, mainFrame.panel);

        NormalSlider globalVolume = new NormalSlider(new Dimension(20,10)) {
            @Override
            public Coordinates slide(Coordinates deltaCoordinates){
                Settings.globalVolume = getValue()*100;
                AudioPlayer.updateSound();
                return super.slide(deltaCoordinates);
            }
        };
        globalVolume.coordinates = new Coordinates(30,5);
        globalVolume.setValue(Settings.globalVolume/100);
        addDisplayComponent(globalVolume, mainFrame.panel);

        NormalSlider soundVolume = new NormalSlider(new Dimension(20,10)) {
            @Override
            public Coordinates slide(Coordinates deltaCoordinates) {
                Settings.soundVolume = getValue() * 100;
                AudioPlayer.updateSound();
                return super.slide(deltaCoordinates);
            }
        };
        soundVolume.coordinates = new Coordinates(30,20);
        soundVolume.setValue(Settings.soundVolume/100);
        addDisplayComponent(soundVolume, mainFrame.panel);

        NormalSlider musicVolume = new NormalSlider(new Dimension(20,10)) {
            @Override
            public Coordinates slide(Coordinates deltaCoordinates) {
                Settings.musicVolume = getValue() * 100;
                AudioPlayer.updateSound();
                return super.slide(deltaCoordinates);
            }
        };
        musicVolume.coordinates = new Coordinates(30,35);
        musicVolume.setValue(Settings.musicVolume/100);
        addDisplayComponent(musicVolume, mainFrame.panel);

        NormalButton backButton = new NormalButton("Back", 5, new Dimension(20, 10)) {
            @Override
            public void clicked() {
                Settings.globalVolume = globalVolumeV;
                Settings.soundVolume = soundVolumeV;
                Settings.musicVolume = musicVolumeV;
                AudioPlayer.updateSound();
                Controller.back();
            }
        };
        backButton.coordinates = new Coordinates(15, 80);
        addDisplayComponent(backButton, mainFrame.panel);

        NormalButton saveButton = new NormalButton("Save", 5, new Dimension(20, 10)) {
            @Override
            public void clicked() {
                Settings.globalVolume = globalVolume.getValue()*100;
                Settings.soundVolume = soundVolume.getValue()*100;
                Settings.musicVolume = musicVolume.getValue()*100;
                musicVolumeV = Settings.musicVolume;
                soundVolumeV = Settings.soundVolume;
                globalVolumeV = Settings.globalVolume;
                Settings.save();
                //Controller.back();
            }
        };
        saveButton.coordinates = new Coordinates(65,80);
        addDisplayComponent(saveButton, mainFrame.panel);

        NormalButton windowMode = new NormalButton(getWindowMode(), 5, new Dimension(20, 10)) {
            @Override
            public void clicked() {
                Settings.windowedMode = !Settings.windowedMode;
                this.label.text=getWindowMode();
            }
        };
        windowMode.coordinates = new Coordinates(30,50);
        addDisplayComponent(windowMode, mainFrame.panel);

        NormalButton playEpic = new NormalButton("Dze se kupas", 3, new Dimension(20,10)) {
            @Override
            public void clicked() {
                AudioPlayer.play(MusicPlayer.TEST);
            }
        };
        playEpic.coordinates = new Coordinates(75,10);
        addDisplayComponent(playEpic, mainFrame.panel);
    }

    private static String getWindowMode() {
        if(Settings.windowedMode)return "Windowed";
            return "Fullscreen";
    }

}
