package audio;

import javafx.embed.swing.JFXPanel;
import settings.Settings;

import java.nio.file.Paths;

public class AudioPlayer {
    // This is a "static" object providing sound and music playing functions

    // MediaPlayer from javafx library needs Toolkit to be initialized, JFXPanel does it for us.
    private static final JFXPanel panel = new JFXPanel();

    public static final String FILEPATH_MUSIC = Paths.get("10Cent","src","resources","audio","songs","").toString();
    public static final String FILEPATH_SOUND = Paths.get("10Cent","src","resources","audio","sounds","").toString();

    private static MusicPlayer[] musicPlayers = MusicPlayer.values();
    private static SoundPlayer[] soundPlayers = SoundPlayer.values();

    public static void setup(){

    }

    // MusicPlayer methods
    public static void play(MusicPlayer musicPlayer){musicPlayer.mediaPlayer.play();}

    public static void pause(MusicPlayer musicPlayer) {musicPlayer.mediaPlayer.pause();}

    public static void stop(MusicPlayer musicPlayer) {musicPlayer.mediaPlayer.stop();}


    // SoundPlayer methods
    public static void play(SoundPlayer soundPlayer) {
        soundPlayer.mediaPlayer.stop();
        soundPlayer.mediaPlayer.play();
    }

    public static void pause(SoundPlayer soundPlayer) {soundPlayer.mediaPlayer.pause();}

    public static void stop(SoundPlayer soundPlayer) {soundPlayer.mediaPlayer.stop();}

    // Other methods
    public static void updateSound(){
        for(MusicPlayer player : musicPlayers){
            player.mediaPlayer.setVolume(Settings.globalVolume*player.localVolume*Settings.musicVolume/1000);
        }
        for(SoundPlayer player : soundPlayers){
            player.mediaPlayer.setVolume(Settings.globalVolume*player.localVolume*Settings.soundVolume/1000);
        }
    }

}
