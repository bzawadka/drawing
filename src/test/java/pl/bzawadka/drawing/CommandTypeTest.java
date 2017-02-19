package pl.bzawadka.drawing;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.CommandType.*;

public class CommandTypeTest {

    @Test
    public void commandCharacterIsChecked() {
        assertThatThrownBy(() -> CommandType.parseFrom('Z'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unrecognized command: Z");
    }

    @Test
    public void commandCanBeParsed() {
        assertThat(CommandType.parseFrom('C')).isEqualTo(CREATE_CANVAS);
        assertThat(CommandType.parseFrom('c')).isEqualTo(CREATE_CANVAS);
        assertThat(CommandType.parseFrom('L')).isEqualTo(DRAW_LINE);
        assertThat(CommandType.parseFrom('l')).isEqualTo(DRAW_LINE);
        assertThat(CommandType.parseFrom('R')).isEqualTo(DRAW_RECTANGLE);
        assertThat(CommandType.parseFrom('r')).isEqualTo(DRAW_RECTANGLE);
        assertThat(CommandType.parseFrom('B')).isEqualTo(BUCKET_FILL);
        assertThat(CommandType.parseFrom('b')).isEqualTo(BUCKET_FILL);
    }
}