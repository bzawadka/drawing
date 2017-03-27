package pl.bzawadka.drawing.command;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.FileUtils.fileContent;
import static pl.bzawadka.drawing.command.CommandType.DRAW_RECTANGLE;

public class DrawRectangleCommandTest {

    @Test
    public void drawRectangleCommandCanBeInstantiated() throws Exception {
        DrawRectangleCommand command = new DrawRectangleCommand(new Invoker(), ImmutableList.of(14, 1, 18, 3));
        assertThat(command.commandType).isEqualTo(DRAW_RECTANGLE);
        assertThat(command.parameters).containsOnly(14, 1, 18, 3);
    }

    @Test
    public void executeDrawsRectangleOnCanvas() {
        Invoker invoker = new Invoker();
        invoker.initializeCanvas(20, 4);
        DrawRectangleCommand command = new DrawRectangleCommand(invoker, ImmutableList.of(14, 1, 18, 3));
        command.execute();
        assertThat(invoker.drawCanvas()).isEqualTo(fileContent("canvas_20x4_with_rectangle.txt"));
    }

}