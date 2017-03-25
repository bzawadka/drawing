package pl.bzawadka.drawing.runner;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.Canvas.canvas;

public class CommandFactoryTest {

    @Test
    public void quitCommandCanBeParsed() {
        Command command = CommandFactory.parse("q", canvas(10, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(QuitCommand.class);

        command = CommandFactory.parse("Q", canvas(10, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(QuitCommand.class);
    }

    @Ignore
    @Test
    public void unrecognizedCommandWillThrowAnException() {
        assertThatThrownBy(() -> CommandFactory.parse("z", canvas(10, 10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void unrecognizedCommandWillReturnNull() {
        Command command = CommandFactory.parse("a", canvas(10, 10));
        assertThat(command).isNull();
    }

}