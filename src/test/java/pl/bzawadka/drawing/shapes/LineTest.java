package pl.bzawadka.drawing.shapes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.shapes.Line.line;
import static pl.bzawadka.drawing.shapes.Point.point;

public class LineTest {

    @Test
    public void lineMustBeVerticalOrHorizontal() {
        assertThatThrownBy(() -> line(1, 2, 3, 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line must be vertical or horizontal");
        assertThatThrownBy(() -> line(2, 2, 2, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Two points with the same coordinates do not create a line");
    }

    //TODO what about a line with negative coordinates?

    @Test
    public void pointsAreCalculatedForHorizontalLine() {
        Line line = line(1, 2, 6, 2);
        assertThat(line.getCharacter()).isEqualTo('x');
        assertThat(line.getPoints())
                .containsOnly(point(1, 2), point(2, 2), point(3, 2), point(4, 2), point(5, 2), point(6, 2));
    }

    @Test
    public void pointsAreCalculatedForVerticalLine() {
        Line line = line(6, 2, 6, 4);
        assertThat(line.getCharacter()).isEqualTo('x');
        assertThat(line.getPoints())
                .containsOnly(point(6, 2), point(6, 3), point(6, 4));
    }
}