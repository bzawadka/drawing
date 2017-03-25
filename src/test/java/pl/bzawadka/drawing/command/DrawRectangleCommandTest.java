package pl.bzawadka.drawing.command;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import pl.bzawadka.drawing.Canvas;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.Canvas.canvas;
import static pl.bzawadka.drawing.FileUtils.fileContent;
import static pl.bzawadka.drawing.command.CommandType.DRAW_RECTANGLE;

public class DrawRectangleCommandTest {

    @Test
    public void drawRectangleCommandCanBeInstantiated() throws Exception {
        DrawRectangleCommand command = new DrawRectangleCommand(canvas(20, 4), ImmutableList.of(14, 1, 18, 3));
        assertThat(command.commandType).isEqualTo(DRAW_RECTANGLE);
    }

    @Test
    public void executeDrawsRectangleOnCanvas() {
        Canvas canvas = canvas(20, 4);
        DrawRectangleCommand command = new DrawRectangleCommand(canvas, ImmutableList.of(14, 1, 18, 3));
        command.execute();
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_rectangle.txt"));
    }

}