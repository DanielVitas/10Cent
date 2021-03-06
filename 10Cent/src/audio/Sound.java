package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public enum Sound {

    /*
    Sounds are shorter than music and don't cycle. They are short and usually of "wav" format.
     */

    BUTTON("button.wav", 1);

    double volume;
    public Clip clip;

    Sound(String fileName, double volume) {
        this.volume = volume;

        try {
            // setting up sound is somewhat lengthy, but it's more consistent and faster than with Clips
            File soundFile = new File(Paths.get(AudioPlayer.SOUNDS_PATH, fileName).toString());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // package private - always play sounds with AudioPlayer.play
    void play() {
        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
    }

}
