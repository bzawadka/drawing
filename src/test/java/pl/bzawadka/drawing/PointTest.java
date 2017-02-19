package pl.bzawadka.drawing;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    public void pointFieldsAreSet() {
        Point point = new Point(-1, 2, 'X');
        assertThat(point.x).isEqualTo(-1);
        assertThat(point.y).isEqualTo(2);
        assertThat(point.character).isEqualTo('X');
    }

}
