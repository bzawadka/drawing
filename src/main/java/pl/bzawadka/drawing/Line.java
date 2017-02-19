package pl.bzawadka.drawing;

import org.apache.commons.lang3.Validate;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Line implements Drawing {
    private static final char CHARACTER = 'x';

    public final int x1;
    public final int y1;
    public final int x2;
    public final int y2;

    public Line(int x1, int y1, int x2, int y2) {
        Validate.isTrue(x1 == x2 || y1 == y2, "Line must be vertical or horizontal");
        Validate.isTrue(x1 != x2 || y1 != y2, "Two points with the same coordinates do not create a line");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public static Line line(int x1, int y1, int x2, int y2) {
        return new Line(x1, y1, x2, y2);
    }

    @Override
    public Set<Point> getPoints() {
        return getPointsOfVerticalOrHorizontalLine();
    }

    private Set<Point> getPointsOfVerticalOrHorizontalLine() {
        Set<Point> points = new HashSet<>();
        for (int x = min(x1, x2); x <= max(x1, x2); x++)
            for (int y = min(y1, y2); y <= max(y1, y2); y++)
                points.add(new Point(x, y));
        return points;
    }

    @Override
    public char getCharacter() {
        return CHARACTER;
    }
}
