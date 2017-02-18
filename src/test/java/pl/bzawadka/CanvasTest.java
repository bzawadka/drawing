package pl.bzawadka;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class CanvasTest {

    @Test
    public void canvasOfCorrectSizeIsDrawn() {
        Canvas canvas = new Canvas(20, 4);
        String expectedCanvasOutput = readFile("canvas.txt");
        assertThat(canvas.draw()).isEqualTo(expectedCanvasOutput);
    }

    private String readFile(String fileName) {
        String content = null;
        try {
            URL resource = this.getClass().getResource(fileName);
            content = FileUtils.readFileToString(new File(resource.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return content;
    }

}
