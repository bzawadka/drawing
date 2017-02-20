package pl.bzawadka.drawing;

import org.apache.commons.lang3.Validate;
import pl.bzawadka.drawing.shapes.Drawing;
import pl.bzawadka.drawing.shapes.Point;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static pl.bzawadka.drawing.shapes.Point.point;

public class Canvas implements DrawableArea {
    private static final char AXIS_X_CHARACTER = '-';
    private static final char AXIS_Y_CHARACTER = '|';
    private static final char EMPTY_CHARACTER = ' ';
    private static final char LINE_SEPARATOR = '\n';

    private final int width;
    private final int height;

    private Map<Point, Character> paintedCharacters;

    private Canvas(int width, int height) {
        Validate.isTrue(width > 0, "Width of the canvas must be greater than 0");
        Validate.isTrue(width <= 100, "Width of the canvas must be smaller or equal to 100");
        Validate.isTrue(height > 0, "Height of the canvas must be greater than 0");
        Validate.isTrue(height <= 100, "Height of the canvas must be smaller or equal to 100");
        this.width = width;
        this.height = height;
        this.paintedCharacters = new HashMap<>();
    }

    public static Canvas canvas(int width, int height) {
        return new Canvas(width, height);
    }

    @Override
    public void place(Drawing drawing) {
        drawing.getPoints().forEach(point -> placePointOnCanvas(point, drawing.getCharacter()));
    }

    @Override
    public String draw() {
        StringBuffer drawingArea = new StringBuffer();
        appendXaxisLineTo(drawingArea);
        for (int y = 1; y <= height; y++) {
            appendYaxisCharacterTo(drawingArea);
            for (int x = 1; x <= width; x++) {
                Point point = point(x, y);
                if (pointIsPaintedOnCanvas(point)) {
                    drawingArea.append(paintedCharacters.get(point));
                } else {
                    drawingArea.append(EMPTY_CHARACTER);
                }
            }
            appendYaxisCharacterTo(drawingArea);
            drawingArea.append(LINE_SEPARATOR);
        }
        appendXaxisLineTo(drawingArea);
        return drawingArea.toString();
    }

    public void bucketFill(Point startingPoint, char character) {
        if (!pointIsWithinCanvas(startingPoint))
            return;

        Deque<Point> pointsToVisit = new ArrayDeque<>();
        pointsToVisit.push(startingPoint);
        while (!pointsToVisit.isEmpty()) {
            Point p = pointsToVisit.pop();
            if (pointIsNotYetPainted(p)) {
                placePointOnCanvas(p, character);
            }
            for (Point neighbour : p.getNeighbours()) {
                if (pointIsNotYetPainted(neighbour) && pointIsWithinCanvas(neighbour))
                    pointsToVisit.push(neighbour);
            }
        }
    }

    private boolean pointIsWithinCanvas(Point point) {
        return point.x > 0 && point.y > 0 && point.x <= width && point.y <= height;
    }

    private boolean pointIsNotYetPainted(Point p) {
        return !pointIsPaintedOnCanvas(p);
    }

    private boolean pointIsPaintedOnCanvas(Point point) {
        return paintedCharacters.containsKey(point);
    }

    private void placePointOnCanvas(Point coordinates, char character) {
        paintedCharacters.put(coordinates, character);
    }

    private void appendYaxisCharacterTo(StringBuffer drawingArea) {
        drawingArea.append(AXIS_Y_CHARACTER);
    }

    private void appendXaxisLineTo(StringBuffer drawingArea) {
        for (int x = 0; x <= width + 1; x++) {
            drawingArea.append(AXIS_X_CHARACTER);
        }
        drawingArea.append(LINE_SEPARATOR);
    }
}
