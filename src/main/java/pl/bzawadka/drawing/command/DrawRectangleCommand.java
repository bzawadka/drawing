package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Canvas;
import pl.bzawadka.drawing.shapes.Drawing;

import java.util.List;

import static pl.bzawadka.drawing.shapes.Rectangle.rectangle;

public class DrawRectangleCommand implements Command {

    public static final CommandType commandType = CommandType.DRAW_RECTANGLE;
    public List<Integer> parameters;

    private final Canvas canvas;

    public DrawRectangleCommand(Canvas canvas, List<Integer> lineCoordinates) {
        this.canvas = canvas;
        this.parameters = lineCoordinates;
    }

    @Override
    public void execute() {
        Drawing rectangle = rectangle(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
        canvas.place(rectangle);
    }
}
