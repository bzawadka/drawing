package pl.bzawadka.drawing;

import org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;

public class Canvas implements DrawableArea {
    private static final char AXIS_X_CHARACTER = '-';
    private static final char AXIS_Y_CHARACTER = '|';
    private static final char EMPTY_CHARACTER = ' ';

    private final int width;
    private final int height;

    private Map<Point, Character> paintedPoints;

    public Canvas(int width, int height) {
        Validate.isTrue(width > 0, "Width of the canvas must be greater than 0");
        Validate.isTrue(width <= 100, "Width of the canvas must be smaller or equal to 100");
        Validate.isTrue(height > 0, "Height of the canvas must be greater than 0");
        Validate.isTrue(height <= 100, "Height of the canvas must be smaller or equal to 100");
        this.width = width;
        this.height = height;
        this.paintedPoints = new HashMap<>();
    }

    @Override
    public void place(Drawing drawing) {
        drawing.getPoints().forEach(point -> placePointOnCanvas(point, drawing.getCharacter()));
    }

    private void placePointOnCanvas(Point point, char character) {
        paintedPoints.put(point, character);
    }

    public String draw() {
        StringBuffer drawingArea = new StringBuffer();
        drawXaxisLineOn(drawingArea);
        for (int y = 1; y <= height; y++) {
            drawYaxisCharacterOn(drawingArea);
            for (int x = 1; x <= width; x++) {
                Point point = new Point(x, y);
                if (paintedPoints.containsKey(point)) {
                    drawingArea.append(paintedPoints.get(point));
                } else {
                    drawingArea.append(EMPTY_CHARACTER);
                }
            }
            drawYaxisCharacterOn(drawingArea);
            drawingArea.append("\n");
        }
        drawXaxisLineOn(drawingArea);
        return drawingArea.toString();
    }

    private void drawYaxisCharacterOn(StringBuffer drawingArea) {
        drawingArea.append(AXIS_Y_CHARACTER);
    }

    private void drawXaxisLineOn(StringBuffer drawingArea) {
        for (int x = 0; x <= width + 1; x++) {
            drawingArea.append(AXIS_X_CHARACTER);
        }
        drawingArea.append("\n");
    }
}
