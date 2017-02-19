package pl.bzawadka.drawing;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.Point.point;
import static pl.bzawadka.drawing.Rectangle.rectangle;
import static pl.bzawadka.drawing.RectangleAssert.assertThat;

public class RectangleTest {

    @Test
    public void pointsMustNotBeInOneLine() {
        assertThatThrownBy(() -> rectangle(14, 1, 18, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Points must not be in one line");
        assertThatThrownBy(() -> rectangle(18, 1, 18, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Points must not be in one line");
        assertThatThrownBy(() -> rectangle(18, 18, 18, 18))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Points must not be in one line");
    }

    @Test
    public void pointsAreCalculated() {
        assertThat(rectangle(14, 1, 18, 3))
                .hasCharacter('x')
                .hasOnlyPoints(
                        point(14, 1), point(15, 1), point(16, 1), point(17, 1), point(18, 1),
                        point(14, 2), point(18, 2),
                        point(14, 3), point(15, 3), point(16, 3), point(17, 3), point(18, 3));
    }

}