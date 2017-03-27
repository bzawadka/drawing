package pl.bzawadka.drawing.command;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.FileUtils.fileContent;
import static pl.bzawadka.drawing.command.CommandType.DRAW_LINE;

public class DrawLineCommandTest {

    @Test
    public void drawLineCommandCanBeInstantiated() throws Exception {
        DrawLineCommand command = new DrawLineCommand(new Invoker(), ImmutableList.of(1, 2, 6, 2));
        assertThat(command.commandType).isEqualTo(DRAW_LINE);
        assertThat(command.parameters).containsOnly(1, 2, 6, 2);
    }

    @Test
    public void executeDrawsLineOnCanvas() {
        Invoker invoker = new Invoker();
        invoker.initializeCanvas(20, 4);
        DrawLineCommand command = new DrawLineCommand(invoker, ImmutableList.of(1, 2, 6, 2));
        command.execute();
        assertThat(invoker.drawCanvas()).isEqualTo(fileContent("canvas_20x4_with_1line.txt"));
    }

}