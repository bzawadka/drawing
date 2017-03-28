package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Command;
import pl.bzawadka.drawing.Receiver;
import pl.bzawadka.drawing.shapes.Point;

import java.util.List;

import static pl.bzawadka.drawing.shapes.Point.point;

public class BucketFillCommand implements Command {

    public static final CommandType commandType = CommandType.BUCKET_FILL;
    public List<Integer> parameters;
    public char character;

    private final Receiver receiver;

    public BucketFillCommand(Receiver receiver, List<Integer> startingPointCoordinates, Character character) {
        this.receiver = receiver;
        this.parameters = startingPointCoordinates;
        this.character = character;
    }

    @Override
    public void execute() {
        Point startingPoint = point(parameters.get(0), parameters.get(1));
        receiver.canvasBucketFill(startingPoint, character);
    }
}
