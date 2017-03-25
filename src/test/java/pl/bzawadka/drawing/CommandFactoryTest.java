package pl.bzawadka.drawing;

import org.junit.Ignore;
import org.junit.Test;
import pl.bzawadka.drawing.command.*;

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

    @Test
    public void drawLineCommandCanBeParsed() {
        Command command = CommandFactory.parse("L 1 2 6 2", canvas(10, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(DrawLineCommand.class);
        assertThat(((DrawLineCommand) command).parameters).containsOnly(1, 2, 6, 2);
    }

    @Test
    public void drawRectangleCommandCanBeParsed() {
        Command command = CommandFactory.parse("R 14 1 18 3", canvas(20, 20));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(DrawRectangleCommand.class);
        assertThat(((DrawRectangleCommand) command).parameters).containsOnly(14, 1, 18, 3);
    }

    @Test
    public void bucketFillCommandCabBeParsed() {
        Command command = CommandFactory.parse("B 10 3 o", canvas(20, 20));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(BucketFillCommand.class);
        assertThat(((BucketFillCommand) command).parameters).containsOnly(10, 3);
        assertThat(((BucketFillCommand) command).character).isEqualTo('o');
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