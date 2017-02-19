package pl.bzawadka.drawing;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.LineAssert.assertThat;

public class LineTest {

    @Test
    public void lineFieldsAreSet() {
        Line line = new Line(1, 2, 6, 2);
        assertThat(line).hasX1(1);
        assertThat(line).hasY1(2);
        assertThat(line).hasX2(6);
        assertThat(line).hasY2(2);
    }

    @Test
    public void lineMustBeVerticalOrHorizontal() {
        assertThatThrownBy(() -> new Line(1, 2, 3, 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line must be vertical or horizontal");
    }

}