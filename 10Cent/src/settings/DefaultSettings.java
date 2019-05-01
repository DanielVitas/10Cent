package settings;

import oracle.jrockit.jfr.openmbean.EventSettingType;

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

    static void setDefaultSettings() {
        Settings.globalVolume = DEFAULT_GLOBAL_VOLUME;
        Settings.soundVolume = DEFAULT_SOUND_VOLUME;
        Settings.musicVolume = DEFAULT_MUSIC_VOLUME;
        Settings.windowedMode = DEFAULT_WINDOWED_MODE;
        Settings.windowSizeX = DEFAULT_WINDOW_SIZE_X;
        Settings.windowSizeY = DEFAULT_WINDOW_SIZE_Y;
        Settings.windowLocationX = DEFAULT_WINDOW_LOCATION_X;
        Settings.windowLocationY = DEFAULT_WINDOW_LOCATION_Y;
        Settings.write();
        Settings.setup();
    }

    static void writeDefaultHead(PrintWriter printWriter) {
        printWriter.println("# Corrupting settings will restore them to their defaults");
        printWriter.println();
    }
}
