package pl.bzawadka.drawing.command;

import org.junit.Test;
import pl.bzawadka.drawing.Canvas;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.Canvas.canvas;
import static pl.bzawadka.drawing.command.CommandType.QUIT;

public class QuitCommandTest {

    @Test
    public void quitCommandCanBeInstantiated() throws Exception {
        QuitCommand command = new QuitCommand(canvas(20, 10));
        assertThat(command.commandType).isEqualTo(QUIT);
    }

    @Test
    public void quitCommandExecutionTurnsCanvasIntoTerminalState() {
        Canvas canvas = canvas(20, 4);

        QuitCommand command = new QuitCommand(canvas);
        assertThat(canvas.isComplete()).isFalse();
        command.execute();
        assertThat(canvas.isComplete()).isTrue();
    }

}