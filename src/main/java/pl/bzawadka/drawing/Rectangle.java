package pl.bzawadka.drawing;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.Validate;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.bzawadka.drawing.Line.line;

public class Rectangle implements Drawing {
    private static final char CHARACTER = 'x';
    private final Set<Point> points;

    public Rectangle(int x1, int y1, int x2, int y2) {
        Validate.isTrue(x1 != x2 && y1 != y2, "Points must not be in one line");
        points = calculatePointsOfRectangle(x1, y1, x2, y2);
    }

    @Override
    public char getCharacter() {
        return CHARACTER;
    }

    @Override
    public Set<Point> getPoints() {
        return ImmutableSet.copyOf(points);
    }

    private Set<Point> calculatePointsOfRectangle(int x1, int y1, int x2, int y2) {
        ImmutableList<Line> rectangleBorders = ImmutableList.of(
                line(x1, y1, x2, y1),
                line(x1, y2, x2, y2),
                line(x1, y1, x1, y2),
                line(x2, y1, x2, y2));
        return rectangleBorders.stream()
                .map(Line::getPoints)
                .flatMap(pointsSet -> pointsSet.stream())
                .collect(Collectors.toSet());
    }

    public static Rectangle rectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle(x1, y1, x2, y2);
    }
}
