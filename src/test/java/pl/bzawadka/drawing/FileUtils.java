package pl.bzawadka.drawing;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtils {

    public static String fileContent(String fileName) {
        String content = null;
        try {
            URL resource = FileUtils.class.getResource(fileName);
            content = org.apache.commons.io.FileUtils.readFileToString(new File(resource.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return content;
    }
}
