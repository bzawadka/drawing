package pl.bzawadka.drawing.shapes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.shapes.Point.point;
import static pl.bzawadka.drawing.shapes.Rectangle.rectangle;

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
        Rectangle rectangle = rectangle(14, 1, 18, 3);
        assertThat(rectangle.getCharacter()).isEqualTo('x');
        assertThat(rectangle.getPoints()).containsOnly(
                point(14, 1), point(15, 1), point(16, 1), point(17, 1), point(18, 1),
                point(14, 2), point(18, 2),
                point(14, 3), point(15, 3), point(16, 3), point(17, 3), point(18, 3));
    }

}