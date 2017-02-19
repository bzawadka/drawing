package pl.bzawadka.drawing;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Set;

public class Point {
    public final int x;
    public final int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point point(int x, int y) {
        return new Point(x, y);
    }

    public Set<Point> getNeighbours() {
        return ImmutableSet.of(
                point(x, y - 1),
                point(x, y + 1),
                point(x + 1, y - 1),
                point(x + 1, y + 1),
                point(x - 1, y - 1),
                point(x - 1, y + 1),
                point(x + 1, y),
                point(x - 1, y)
        );
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return String.format("[x=%s,y=%s]", x, y);
    }
}
