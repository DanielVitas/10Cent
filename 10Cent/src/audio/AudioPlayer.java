package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public enum AudioPlayer {
    /* string points to file name, soundPercent allows us to set
    loudness of certain file in the code and audioType tells if
    it is a sound or a song
     */
    TEST("test.mp3",1 ,false),
    TESTT("test.wav",1,true);

    private static final String FILEPATH_MUSIC = "10Cent/src/resources/audio/songs/";
    private static final String FILEPATH_SOUND = "10Cent/src/resources/audio/sounds/";

    private final double localSound;
    private static double globalSound;

    private MediaPlayer mediaPlayer;
    private final boolean audioType;

    AudioPlayer(String string, int soundPercent, Boolean audioType){
        this.localSound = soundPercent;
        this.audioType = audioType;
        if (audioType){
            Media media = new Media(new File(FILEPATH_SOUND + string).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        } else {
            Media media = new Media(new File(FILEPATH_MUSIC + string).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(localSound);
            //TODO mediaPlayer.setOnEndOfMedia();
        }
    }

    public void play(){
        if(audioType){
            mediaPlayer.stop();
            mediaPlayer.play();
        }
        mediaPlayer.play();
    }

    public void pause() {mediaPlayer.pause();} //maybe use currentlyPlaying

    public void stop() {
        mediaPlayer.stop();
    }

    public void setGlobalSound(double percent){globalSound = percent;}

    public void setVolume(double percent) {mediaPlayer.setVolume(percent*localSound*globalSound);}

}
