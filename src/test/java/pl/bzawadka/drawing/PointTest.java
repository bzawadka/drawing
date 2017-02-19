package pl.bzawadka.drawing;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    public void pointFieldsAreSet() {
        Point point = new Point(-1, 2, 'X');
        assertThat(point.x).isEqualTo(-1);
        assertThat(point.y).isEqualTo(2);
        assertThat(point.character).isEqualTo('X');
    }

    @Test
    public void pointCanBeUsedInCollection() {
        Point p1 = new Point(1, 2, 'X');
        Point p2 = new Point(1, 2, 'X');
        assertThat(p1).isEqualTo(p2);

        Set<Point> points = new HashSet<>();
        points.add(p1);
        points.add(p2);
        assertThat(points.size()).isEqualTo(1);
    }

}
