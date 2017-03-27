package pl.bzawadka.drawing.command;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.FileUtils.fileContent;
import static pl.bzawadka.drawing.command.CommandType.CREATE_CANVAS;

public class CreateCanvasCommandTest {

    @Test
    public void createCanvasCommandCanBeInstantiated() throws Exception {
        CreateCanvasCommand command = new CreateCanvasCommand(new Invoker(), ImmutableList.of(20, 10));
        assertThat(command.commandType).isEqualTo(CREATE_CANVAS);
        assertThat(command.parameters).containsOnly(20, 10);
    }

    @Test
    public void executeCreatesEmptyCanvas() {
        Invoker invoker = new Invoker();
        CreateCanvasCommand command = new CreateCanvasCommand(invoker, ImmutableList.of(20, 4));
        command.execute();
        assertThat(invoker.drawCanvas()).isEqualTo(fileContent("canvas_20x4.txt"));
    }

}