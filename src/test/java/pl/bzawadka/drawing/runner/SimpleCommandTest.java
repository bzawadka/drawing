package pl.bzawadka.drawing.runner;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.command.CommandType.BUCKET_FILL;
import static pl.bzawadka.drawing.command.CommandType.CREATE_CANVAS;

public class SimpleCommandTest {

    @Test
    public void commandCanBeParsedOnlyFromSpecificFormat() {
        assertThatThrownBy(() -> SimpleCommand.parse("20 4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
        assertThatThrownBy(() -> SimpleCommand.parse("A 20 4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
    }

    @Test
    public void commandIsParsed() {
        SimpleCommand command = SimpleCommand.parse("C 20 4");
        assertThat(command.key).isEqualTo('C');
        assertThat(command.commandType).isEqualTo(CREATE_CANVAS);
        assertThat(command.parameters).containsOnly(20, 4);

        command = SimpleCommand.parse("B 10 3 o");
        assertThat(command.key).isEqualTo('B');
        assertThat(command.commandType).isEqualTo(BUCKET_FILL);
        assertThat(command.parameters).containsOnly(10, 3);
        assertThat(command.character).isEqualTo(Optional.of('o'));
    }

    @Test
    public void lowerCaseCommandIsParsed() {
        SimpleCommand command = SimpleCommand.parse("c 20 4");
        assertThat(command.key).isEqualTo('C');
        assertThat(command.commandType).isEqualTo(CREATE_CANVAS);
    }

}