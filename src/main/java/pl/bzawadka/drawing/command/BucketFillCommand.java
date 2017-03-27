package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.shapes.Point;

import java.util.List;

import static pl.bzawadka.drawing.shapes.Point.point;

public class BucketFillCommand implements Command {

    public static final CommandType commandType = CommandType.BUCKET_FILL;
    public List<Integer> parameters;
    public char character;

    private final Invoker invoker;

    public BucketFillCommand(Invoker invoker, List<Integer> startingPointCoordinates, Character character) {
        this.invoker = invoker;
        this.parameters = startingPointCoordinates;
        this.character = character;
    }

    @Override
    public void execute() {
        Point startingPoint = point(parameters.get(0), parameters.get(1));
        invoker.canvasBucketFill(startingPoint, character);
    }
}
