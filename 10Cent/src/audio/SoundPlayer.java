package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public enum SoundPlayer {
    TEST("test.wav", 1);

    private static final String FILEPATH_SOUND = "10Cent/src/resources/audio/sounds/";

    final double localSound;
    MediaPlayer mediaPlayer;

    SoundPlayer(String fileName, double soundPercent){
        this.localSound = soundPercent;
        Media media = new Media(new File(FILEPATH_SOUND + fileName).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(localSound);
    }
}
