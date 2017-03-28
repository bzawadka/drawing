package pl.bzawadka.drawing.command;

import org.junit.Test;
import pl.bzawadka.drawing.Receiver;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.command.CommandType.QUIT;

public class QuitCommandTest {

    @Test
    public void quitCommandCanBeInstantiated() throws Exception {
        QuitCommand command = new QuitCommand(new Receiver());
        assertThat(command.commandType).isEqualTo(QUIT);
    }

    @Test
    public void quitCommandExecutionTurnsCanvasIntoTerminalState() {
        Receiver receiver = new Receiver();
        receiver.initializeCanvas(10, 10);
        QuitCommand command = new QuitCommand(receiver);
        assertThat(receiver.isCanvasComplete()).isFalse();
        command.execute();
        assertThat(receiver.isCanvasComplete()).isTrue();
    }

}