package pl.bzawadka.drawing;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;

public class CanvasTest {

    @Test
    public void canvasSizeIsChecked() {
        assertThatThrownBy(() -> new Canvas(-1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Width of the canvas must be greater than 0");
        assertThatThrownBy(() -> new Canvas(101, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Width of the canvas must be smaller or equal to 100");
        assertThatThrownBy(() -> new Canvas(1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Height of the canvas must be greater than 0");
        assertThatThrownBy(() -> new Canvas(1, 101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Height of the canvas must be smaller or equal to 100");
    }

    @Test
    public void canvasOfCorrectSizeIsDrawn() {
        assertThat(new Canvas(20, 4).draw()).isEqualTo(readFile("canvas_20x4.txt"));
        assertThat(new Canvas(6, 3).draw()).isEqualTo(readFile("canvas_6x3.txt"));
    }

    @Test
    public void lineCanBeDrawnOnCanvas() {
        Canvas canvas = new Canvas(20, 4);
        Drawing drawing = new Line(1, 2, 6, 2);
        canvas.place(drawing);
        assertThat(canvas.draw()).isEqualTo(readFile("canvas_20x4_with_line.txt"));
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
