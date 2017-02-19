package pl.bzawadka.drawing;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.Line.line;
import static pl.bzawadka.drawing.LineAssert.assertThat;
import static pl.bzawadka.drawing.Point.point;

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

    @Test
    public void lineFieldsAreSet() {
        Line line = new Line(1, 2, 6, 2);
        assertThat(line).hasX1(1);
        assertThat(line).hasY1(2);
        assertThat(line).hasX2(6);
        assertThat(line).hasY2(2);
    }

    //TODO what about a line with negative coordinates?

    @Test
    public void pointsAreCalculatedForHorizontalLine() {
        assertThat(line(1, 2, 6, 2))
                .hasOnlyPoints(point(1, 2), point(2, 2), point(3, 2), point(4, 2), point(5, 2), point(6, 2));
    }

    @Test
    public void pointsAreCalculatedForVerticalLine() {
        assertThat(line(6, 2, 6, 4))
                .hasOnlyPoints(point(6, 2), point(6, 3), point(6, 4));
    }
}