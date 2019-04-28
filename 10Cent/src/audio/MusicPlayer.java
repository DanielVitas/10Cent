package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public enum MusicPlayer {
    TEST("test.mp3", 1);

    private static final String FILEPATH_MUSIC = "10Cent/src/resources/audio/songs/";

    final double localSound;
    MediaPlayer mediaPlayer;

    MusicPlayer(String fileName, double soundPercent){
        this.localSound = soundPercent;
        Media media = new Media(new File(FILEPATH_MUSIC + fileName).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(localSound);
    }
}
