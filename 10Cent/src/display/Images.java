package display;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public final class Images {

    public static String resourcesPath = "10Cent/src/resources";

    public static BufferedImage get(String name) {
        return null;
    }

    // returns list of all file names with appropriate suffix ("" - all suffixes) from specified folder
    public static List<String> getFileNames(String folderPath, String suffix) {
        List<String> fileNames = new ArrayList<>();
        File[] fileList = new File(folderPath).listFiles();

        for (File file: fileList) {  // throws exception if fileList is null
            if (!file.isDirectory()) {
                String path = file.getName();
                System.out.println(path);
                if (suffix.equals(""))
                    fileNames.add(path);
                else if (path.substring(path.lastIndexOf(".")).equals(suffix))
                    fileNames.add(path);
            }
        }
        return fileNames;
    }

}
