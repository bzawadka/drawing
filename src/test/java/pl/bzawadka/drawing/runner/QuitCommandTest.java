package pl.bzawadka.drawing.runner;

import org.junit.Test;
import pl.bzawadka.drawing.Canvas;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.Canvas.canvas;
import static pl.bzawadka.drawing.runner.CommandType.QUIT;

public class QuitCommandTest {

    @Test
    public void quitCommandCanBeInstantiated() throws Exception {
        QuitCommand command = new QuitCommand(canvas(10, 10));
        assertThat(command.commandType).isEqualTo(QUIT);
    }

    @Test
    public void quitCommandExecutionFurnsCanvasIntoTerminalState() {
        Canvas canvas = canvas(10, 10);
        QuitCommand command = new QuitCommand(canvas);
        assertThat(canvas.isComplete()).isFalse();
        command.execute();
        assertThat(canvas.isComplete()).isTrue();
    }

}