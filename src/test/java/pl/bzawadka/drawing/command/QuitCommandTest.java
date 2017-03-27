package pl.bzawadka.drawing.command;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.command.CommandType.QUIT;

public class QuitCommandTest {

    @Test
    public void quitCommandCanBeInstantiated() throws Exception {
        QuitCommand command = new QuitCommand(new Invoker());
        assertThat(command.commandType).isEqualTo(QUIT);
    }

    @Test
    public void quitCommandExecutionTurnsCanvasIntoTerminalState() {
        Invoker invoker = new Invoker();
        invoker.initializeCanvas(10, 10);
        QuitCommand command = new QuitCommand(invoker);
        assertThat(invoker.isCanvasComplete()).isFalse();
        command.execute();
        assertThat(invoker.isCanvasComplete()).isTrue();
    }

}