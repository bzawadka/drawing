package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Command;
import pl.bzawadka.drawing.Receiver;
import pl.bzawadka.drawing.shapes.Drawing;

import java.util.List;

import static pl.bzawadka.drawing.shapes.Rectangle.rectangle;

public class DrawRectangleCommand implements Command {

    public static final CommandType commandType = CommandType.DRAW_RECTANGLE;
    public List<Integer> parameters;

    private final Receiver receiver;

    public DrawRectangleCommand(Receiver receiver, List<Integer> lineCoordinates) {
        this.receiver = receiver;
        this.parameters = lineCoordinates;
    }

    @Override
    public void execute() {
        Drawing rectangle = rectangle(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
        receiver.placeDrawing(rectangle);
    }
}
