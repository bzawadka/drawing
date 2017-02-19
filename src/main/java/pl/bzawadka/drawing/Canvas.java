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

    public String draw() {
        StringBuffer buffer = new StringBuffer();
        drawXaxis(buffer);

        for (int y = 0; y < height; y++) {
            buffer.append(AXIS_Y_CHARACTER);
            for (int x = 0; x < width; x++) {
                Point point = new Point(x, y);
                if (paintedPoints.containsKey(point)) {
                    buffer.append(paintedPoints.get(point));
                } else {
                    buffer.append(EMPTY_CHARACTER);
                }
            }
            buffer.append(AXIS_Y_CHARACTER);
            buffer.append("\n");
        }

        drawXaxis(buffer);
        return buffer.toString();
    }

    private void drawXaxis(StringBuffer buffer) {
        for (int x = 0; x < width + 2; x++) {
            buffer.append(AXIS_X_CHARACTER);
        }
        buffer.append("\n");
    }

    public void place(Drawing drawing) {

    }
}
