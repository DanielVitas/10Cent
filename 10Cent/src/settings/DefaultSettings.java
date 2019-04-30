package settings;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;

final class DefaultSettings {

    private final static double DEFAULT_GLOBAL_VOLUME = 100;
    private final static double DEFAULT_SOUND_VOLUME = 100;
    private final static double DEFAULT_MUSIC_VOLUME = 100;
    private final static boolean DEFAULT_WINDOWED_MODE = true;
    private final static int DEFAULT_WINDOW_SIZE_X = 800;
    private final static int DEFAULT_WINDOW_SIZE_Y = 800;
    private final static int DEFAULT_WINDOW_LOCATION_X = 20;
    private final static int DEFAULT_WINDOW_LOCATION_Y = 20;

    static void writeDefault() {
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
            out.println("windowSizeX = " + DEFAULT_WINDOW_SIZE_X);
            out.println("windowSizeY = " + DEFAULT_WINDOW_SIZE_Y);
            out.println("windowLocationX = " + DEFAULT_WINDOW_LOCATION_X);
            out.println("windowLocationY = " + DEFAULT_WINDOW_LOCATION_Y);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        Settings.setup();
    }

    static void writeDefaultHead(PrintWriter printWriter) {
        printWriter.println("# Corrupting settings will restore them to their defaults");
        printWriter.println();
    }
}
