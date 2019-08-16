package audio;

import display.images.Images;
import javafx.embed.swing.JFXPanel;
import settings.Settings;

import javax.sound.sampled.FloatControl;
import java.nio.file.Paths;

public class AudioPlayer {

    /*
    This is a "static" object providing sound and music playing functions.
     */

    // these paths cannot be defined in respective enums
    static final String MUSIC_PATH = Paths.get(Images.RESOURCES_PATH,"audio", "music").toString();
    static final String SOUNDS_PATH = Paths.get(Images.RESOURCES_PATH,"audio", "sounds").toString();

    // stores all music and sounds - used when setting volume
    private static Music[] music;
    private static Sound[] sounds;

    // only one music should be playing at the time
    private static Music currentlyPlayingMusic;

    // called once at the start of the program
    public static void load() {
        new JFXPanel(); // initializes toolkit for playing music

        music = Music.values();
        sounds = Sound.values();
    }

    // plays music
    public static void play(Music music) {
        if (currentlyPlayingMusic != null && currentlyPlayingMusic != music)
            currentlyPlayingMusic.mediaPlayer.stop();
        currentlyPlayingMusic = music;
        music.play();
    }

    // plays sound
    public static void play(Sound sound) {
        sound.play();
    }

    // volume is applied to all sounds beforehand, as it's rarely changed after startup
    public static void applyVolume() {
        for (Music music : music)
            music.mediaPlayer.setVolume(Settings.musicVolume * music.volume * Settings.globalVolume);

        for (Sound sound : sounds) {
            FloatControl floatControl = (FloatControl) sound.clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(scale(Settings.soundVolume * sound.volume * Settings.globalVolume));
        }
    }

    // scales sound's volume as the scale it's applied on is by default exponential - with scale it becomes linear
    private static float scale(double volume) {
        return 20f * (float) Math.log10(volume);
    }

}
