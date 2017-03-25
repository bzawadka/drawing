package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Canvas;
import pl.bzawadka.drawing.shapes.Point;

import java.util.List;

import static pl.bzawadka.drawing.shapes.Point.point;

public class BucketFillCommand implements Command {

    public static final CommandType commandType = CommandType.BUCKET_FILL;
    public List<Integer> parameters;
    public char character;

    private final Canvas canvas;

    public BucketFillCommand(Canvas canvas, List<Integer> startingPointCoordinates, Character character) {
        this.canvas = canvas;
        this.parameters = startingPointCoordinates;
        this.character = character;
    }

    @Override
    public void execute() {
        Point startingPoint = point(parameters.get(0), parameters.get(1));
        canvas.bucketFill(startingPoint, character);
    }
}
