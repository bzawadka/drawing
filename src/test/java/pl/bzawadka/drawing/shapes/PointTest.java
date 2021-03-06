package pl.bzawadka.drawing.shapes;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.shapes.Point.point;

public class PointTest {

    @Test
    public void pointFieldsAreSet() {
        Point point = point(-1, 2);
        assertThat(point.x).isEqualTo(-1);
        assertThat(point.y).isEqualTo(2);
    }

    @Test
    public void pointCanBeUsedInCollection() {
        Point p1 = point(1, 2);
        Point p2 = point(1, 2);
        Assertions.assertThat(p1).isEqualTo(p2);

        Set<Point> points = new HashSet<>();
        points.add(p1);
        points.add(p2);
        Assertions.assertThat(points.size()).isEqualTo(1);
    }

    @Test
    public void neighboursAreCalculated() {
        Point point = point(3, 2);
        assertThat(point.getNeighbours()).containsOnly(
                point(2, 1), point(3, 1), point(4, 1),
                point(2, 2), point(4, 2),
                point(2, 3), point(3, 3), point(4, 3));
    }

}
