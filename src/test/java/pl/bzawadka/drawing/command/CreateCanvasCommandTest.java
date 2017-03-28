package pl.bzawadka.drawing.command;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import pl.bzawadka.drawing.Receiver;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.FileUtils.fileContent;
import static pl.bzawadka.drawing.command.CommandType.CREATE_CANVAS;

public class CreateCanvasCommandTest {

    @Test
    public void createCanvasCommandCanBeInstantiated() throws Exception {
        CreateCanvasCommand command = new CreateCanvasCommand(new Receiver(), ImmutableList.of(20, 10));
        assertThat(command.commandType).isEqualTo(CREATE_CANVAS);
        assertThat(command.parameters).containsOnly(20, 10);
    }

    @Test
    public void executeCreatesEmptyCanvas() {
        Receiver receiver = new Receiver();
        CreateCanvasCommand command = new CreateCanvasCommand(receiver, ImmutableList.of(20, 4));
        command.execute();
        assertThat(receiver.drawCanvas()).isEqualTo(fileContent("canvas_20x4.txt"));
    }

}