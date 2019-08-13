package settings;

import audio.AudioPlayer;
import display.frame.MainFrame;
import display.images.Images;

import java.io.*;
import java.nio.file.Paths;

public final class Settings {

    public static final String SETTINGS_FILE = Paths.get(Images.RESOURCES_PATH, "settings", "settings.txt").toString();

    public static double globalVolume;
    public static double soundVolume;
    public static double musicVolume;
    public static boolean windowedMode;
    public static int[] windowSize;
    public static int[] windowLocation;

    public static void defaults() {
        globalVolume = 50;
        soundVolume = 50;
        musicVolume = 50;
        windowedMode = true;
        windowSize = new int[]{800, 800};
        windowLocation = new int[]{20, 20};
    }

    // initializes settings, this is only called once at the start
    public static void initialize() {
        defaults();
        read();
        AudioPlayer.updateSound();
    }

    public static void save() {
        apply();
        write();
    }

    private static void read() {
        try{
            BufferedReader in = new BufferedReader(new FileReader(SETTINGS_FILE));

            while (in.ready()) {
                String line = in.readLine().trim().replaceAll("\\s", "");

                if (line.equals(""))
                    continue;
                if (line.charAt(0) == '#')
                    continue;

                String[] splitLine = line.split("=");

                if (splitLine.length != 2)
                    continue;

                switch (splitLine[0]){
                    case ("globalVolume"):
                        globalVolume = Double.valueOf(splitLine[1]);
                        break;
                    case ("soundVolume"):
                        soundVolume = Double.valueOf(splitLine[1]);
                        break;
                    case ("musicVolume"):
                        musicVolume = Double.valueOf(splitLine[1]);
                        break;
                    case ("windowedMode"):
                        windowedMode = Boolean.valueOf(splitLine[1]);
                        break;
                    case ("windowSize"):
                        String[] windowSizeXY = splitLine[1].split(",");
                        windowSize = new int[]{Integer.valueOf(windowSizeXY[0]), Integer.valueOf(windowSizeXY[1])};
                        break;
                    case ("windowLocation"):
                        String[] windowLocationXY = splitLine[1].split(",");
                        windowLocation = new int[]{Integer.valueOf(windowLocationXY[0]), Integer.valueOf(windowLocationXY[1])};
                        break;
                }
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace(); // exceptions can arise from user messing with settings file
        }
    }

    private static void apply() {
        MainFrame.switchToWindowed();
        AudioPlayer.updateSound();
    }

    private static void write() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(SETTINGS_FILE));

            out.println("# audio");
            out.println("globalVolume = " + globalVolume);
            out.println("soundVolume = " + soundVolume);
            out.println("musicVolume = " + musicVolume);
            out.println();

            out.println("# screen");
            out.println("windowedMode = " + windowedMode);
            out.println(String.format("windowSize = %d, %d", windowSize[0], windowSize[1]));
            out.println(String.format("windowLocation = %d, %d", windowLocation[0], windowLocation[1]));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
