package pl.bzawadka.drawing;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static pl.bzawadka.drawing.Point.point;
import static pl.bzawadka.drawing.PointAssert.assertThat;

public class PointTest {

    @Test
    public void pointFieldsAreSet() {
        Point point = point(-1, 2);
        assertThat(point).hasX(-1);
        assertThat(point).hasY(2);
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
        assertThat(point).hasOnlyNeighbours(
                point(2, 1), point(3, 1), point(4, 1),
                point(2, 2), point(4, 2),
                point(2, 3), point(3, 3), point(4, 3));
    }

}
