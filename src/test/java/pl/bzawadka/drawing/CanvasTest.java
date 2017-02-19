package pl.bzawadka.drawing;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.Line.line;
import static pl.bzawadka.drawing.Point.point;
import static pl.bzawadka.drawing.Rectangle.rectangle;

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
        assertThat(new Canvas(20, 4).draw()).isEqualTo(fileContent("canvas_20x4.txt"));
        assertThat(new Canvas(6, 3).draw()).isEqualTo(fileContent("canvas_6x3.txt"));
    }

    @Test
    public void linesAndRectangleCanBeDrawnOnCanvas() {
        Canvas canvas = new Canvas(20, 4);

        Drawing horizontalLine = line(1, 2, 6, 2);
        canvas.place(horizontalLine);
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_1line.txt"));

        Drawing verticalLine = line(6, 3, 6, 4);
        canvas.place(verticalLine);
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_2lines.txt"));

        Drawing rectangle = rectangle(14, 1, 18, 3);
        canvas.place(rectangle);
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_2lines_rectangle.txt"));

        canvas.bucketFill(point(10, 3), 'o');
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_2lines_rectangle_filled.txt"));
    }

    private String fileContent(String fileName) {
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
