package pl.bzawadka.drawing.command;

import org.junit.Test;
import pl.bzawadka.drawing.Command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.Canvas.canvas;

public class CommandFactoryTest {

    @Test
    public void quitCommandCanBeParsed() {
        Command command = CommandFactory.parse("q", canvas(20, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(QuitCommand.class);

        command = CommandFactory.parse("Q", canvas(20, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(QuitCommand.class);
    }

    @Test
    public void drawLineCommandCanBeParsed() {
        Command command = CommandFactory.parse("L 1 2 6 2", canvas(20, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(DrawLineCommand.class);
        assertThat(((DrawLineCommand) command).parameters).containsOnly(1, 2, 6, 2);
    }

    @Test
    public void drawRectangleCommandCanBeParsed() {
        Command command = CommandFactory.parse("R 14 1 18 3", canvas(20, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(DrawRectangleCommand.class);
        assertThat(((DrawRectangleCommand) command).parameters).containsOnly(14, 1, 18, 3);
    }

    @Test
    public void bucketFillCommandCanBeParsed() {
        Command command = CommandFactory.parse("B 10 3 o", canvas(20, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(BucketFillCommand.class);
        assertThat(((BucketFillCommand) command).parameters).containsOnly(10, 3);
        assertThat(((BucketFillCommand) command).character).isEqualTo('o');
    }

    @Test
    public void createCanvasCommandCanBeParsed() {
        Command command = CommandFactory.parse("C 20 4", canvas(20, 10));
        assertThat(command).isNotNull();
        assertThat(command).isInstanceOf(CreateCanvasCommand.class);
        assertThat(((CreateCanvasCommand) command).parameters).containsOnly(20, 4);
    }

    @Test
    public void unrecognizedCommandWillThrowAnException() {
        assertThatThrownBy(() -> CommandFactory.parse("z", canvas(20, 10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}