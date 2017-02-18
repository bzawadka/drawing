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
        assertThat(new Canvas(20, 4).draw()).isEqualTo(readFile("canvas_20x4.txt"));
        assertThat(new Canvas(6, 3).draw()).isEqualTo(readFile("canvas_6x3.txt"));
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
