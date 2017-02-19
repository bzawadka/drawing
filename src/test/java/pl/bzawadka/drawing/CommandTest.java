package pl.bzawadka.drawing;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.CommandAssert.assertThat;

public class CommandTest {

    @Test
    public void commandCanBeParsedOnlyFromSpecificFormat() {
        assertThatThrownBy(() -> Command.parse("20 4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
        assertThatThrownBy(() -> Command.parse("A 20 4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
    }

    @Test
    public void commandIsParsed() {
        Command command = Command.parse("C 20 4");
        assertThat(command).hasKey('C');
        assertThat(command).hasOnlyParameters(20, 4);

        command = Command.parse("L 1 2 6 2");
        assertThat(command).hasKey('L');
        assertThat(command).hasOnlyParameters(1, 2, 6, 2);

        command = Command.parse("R 14 1 18 3");
        assertThat(command).hasKey('R');
        assertThat(command).hasOnlyParameters(14, 1, 18, 3);
    }

}