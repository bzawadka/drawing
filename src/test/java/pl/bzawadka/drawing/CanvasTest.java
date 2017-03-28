package pl.bzawadka.drawing;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import pl.bzawadka.drawing.shapes.Drawing;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.Canvas.canvas;
import static pl.bzawadka.drawing.shapes.Line.line;
import static pl.bzawadka.drawing.shapes.Point.point;
import static pl.bzawadka.drawing.shapes.Rectangle.rectangle;

public class CanvasTest {

    @Test
    public void canvasIsInstantiatedAsNotTerminal() {
        assertThat(canvas(10, 10).isComplete()).isFalse();
    }

    @Test
    public void canvasCanBeMarkedAsCompleted() {
        Canvas canvas = canvas(10, 10);
        canvas.complete();
        assertThat(canvas.isComplete()).isTrue();
    }

    @Test
    public void canvasIsInitializedWithNoSize() {
        Canvas canvas = canvas();
        assertThat(canvas.getWidth()).isNull();
        assertThat(canvas.getHeight()).isNull();
    }

    @Test
    public void canvasSizeCanBeAmended() {
        Canvas canvas = canvas();
        canvas.setSize(20, 15);
        assertThat(canvas.getWidth()).isEqualTo(20);
        assertThat(canvas.getHeight()).isEqualTo(15);
    }

    @Test
    public void canvasSizeIsChecked() {
        assertThatThrownBy(() -> canvas(-1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Width of the canvas must be greater than 0");
        assertThatThrownBy(() -> canvas(101, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Width of the canvas must be smaller or equal to 100");
        assertThatThrownBy(() -> canvas(1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Height of the canvas must be greater than 0");
        assertThatThrownBy(() -> canvas(1, 101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Height of the canvas must be smaller or equal to 100");
    }

    @Test
    public void canvasOfCorrectSizeIsDrawn() {
        assertThat(canvas(20, 4).draw()).isEqualTo(fileContent("canvas_20x4.txt"));
        assertThat(canvas(6, 3).draw()).isEqualTo(fileContent("canvas_6x3.txt"));
    }

    @Test
    public void linesCanBeDrawnOnCanvas() {
        Canvas canvas = canvas(20, 4);

        Drawing horizontalLine = line(1, 2, 6, 2);
        canvas.place(horizontalLine);
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_1line.txt"));

        Drawing verticalLine = line(6, 3, 6, 4);
        canvas.place(verticalLine);
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_2lines.txt"));
    }

    @Test
    public void rectangleCanBeDrawnOnCanvas() {
        Canvas canvas = canvas(20, 4);
        Drawing rectangle = rectangle(14, 1, 18, 3);
        canvas.place(rectangle);
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_rectangle.txt"));
    }

    @Test
    public void rectangleCanBeFilledOnCanvas() {
        Canvas canvas = canvas(20, 4);
        Stream.of(line(1, 2, 6, 2), line(6, 3, 6, 4), rectangle(14, 1, 18, 3))
                .forEach(drawing -> canvas.place(drawing));
        canvas.bucketFill(point(10, 3), 'o');
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_rectangle_filled.txt"));
    }

    @Test
    public void coloringRectangles() {
        Canvas canvas = canvas(12, 4);

        Drawing rectangle = rectangle(2, 2, 5, 4);
        canvas.place(rectangle);
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_10x4_with_1rectangle.txt"));

        canvas.bucketFill(point(3, 3), 'Z');
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_10x4_with_1rectangle_filled.txt"));

        rectangle = rectangle(7, 1, 10, 3);
        canvas.place(rectangle);
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_10x4_with_2rectangles.txt"));

        canvas.bucketFill(point(8, 2), 'B');
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_10x4_with_2rectangles_filled.txt"));

        canvas.bucketFill(point(1, 1), 'y');
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_10x4_with_2rectangles_filled_completely.txt"));
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
