package pl.bzawadka.drawing;


import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.Rectangle.rectangle;

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
    public void rectangleFieldsAreSet() {
        Rectangle rectangle = new Rectangle(14, 1, 18, 3);
    }

}