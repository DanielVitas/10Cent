package settings;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;

final class DefaultSettings {

    private final static double DEFAULT_GLOBAL_VOLUME = 100;
    private final static double DEFAULT_SOUND_VOLUME = 100;
    private final static double DEFAULT_MUSIC_VOLUME = 100;
    private final static boolean DEFAULT_WINDOWED_MODE = true;

    static void writeDefault(){
        try{
            PrintWriter out = new PrintWriter(new FileWriter(Settings.SETTINGS_FILE));
            writeDefaultHead(out);
            out.println("#Audio");
            out.println("globalVolume = " + DEFAULT_GLOBAL_VOLUME);
            out.println("soundVolume = " + DEFAULT_SOUND_VOLUME);
            out.println("musicVolume = " + DEFAULT_MUSIC_VOLUME);
            out.println();
            out.println("# Screen");
            out.println("windowedMode = " + DEFAULT_WINDOWED_MODE);
        } catch (Exception e) {
            System.out.println(e);
        }
        Settings.setup();
    }

    static void writeDefaultHead(PrintWriter printWriter){
        printWriter.println("# Corrupting settings will restore them to their defaults");
        printWriter.println();
    }
}
