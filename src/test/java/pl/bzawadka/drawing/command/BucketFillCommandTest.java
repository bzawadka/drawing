package pl.bzawadka.drawing.command;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import pl.bzawadka.drawing.Canvas;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.Canvas.canvas;
import static pl.bzawadka.drawing.FileUtils.fileContent;
import static pl.bzawadka.drawing.command.CommandType.BUCKET_FILL;

public class BucketFillCommandTest {

    @Test
    public void bucketFillCommandCanBeInstantiated() throws Exception {
        BucketFillCommand command = new BucketFillCommand(canvas(10, 10), ImmutableList.of(1, 2), 'z');
        assertThat(command.commandType).isEqualTo(BUCKET_FILL);
        assertThat(command.parameters).containsOnly(1, 2);
        assertThat(command.character).isEqualTo('z');
    }

    @Test
    public void executeFillsBucketOnCanvas() {
        Canvas canvas = canvas(12, 4);

        new DrawRectangleCommand(canvas, ImmutableList.of(2, 2, 5, 4)).execute();
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_10x4_with_1rectangle.txt"));

        new BucketFillCommand(canvas, ImmutableList.of(3, 3), 'Z').execute();
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_10x4_with_1rectangle_filled.txt"));
    }

}