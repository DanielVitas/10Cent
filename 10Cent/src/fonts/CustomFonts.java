package fonts;

import display.images.Images;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class CustomFonts {

    /*
    This class stores all the custom fonts. They should be accessed through static variables.
     */

    private final static String FONT_PATH = Paths.get(Images.RESOURCES_PATH, "fonts").toString();
    private static GraphicsEnvironment ge;
    private static Map<String, Font> fonts = new HashMap<>();

    public final static String CALLIGRAPHY = "calligraphy";
    public final static String PAINTER = "painter";

    // called once at the start of the program
    public static void load() {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        addFont(CALLIGRAPHY);
        addFont(PAINTER);
    }

    // adds ttf file to fonts
    private static void addFont(String fontName) {
        addFont(fontName, "ttf");
    }

    // creates and registers specified font
    private static void addFont(String fontName, String type) {
        File file = new File(Paths.get(FONT_PATH, fontName + "." + type).toString());
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        ge.registerFont(font);
        fonts.put(fontName, font);
    }

    public static Font getFont(String fontName, float size) {
        return fonts.get(fontName).deriveFont(size);
    }

}
