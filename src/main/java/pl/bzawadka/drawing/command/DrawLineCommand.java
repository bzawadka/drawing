package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Canvas;
import pl.bzawadka.drawing.shapes.Drawing;

import java.util.List;

import static pl.bzawadka.drawing.shapes.Line.line;

public class DrawLineCommand implements Command {

    public static final CommandType commandType = CommandType.DRAW_LINE;

    private final Canvas canvas;
    public List<Integer> parameters;

    public DrawLineCommand(Canvas canvas, List<Integer> lineCoordinates) {
        this.canvas = canvas;
        this.parameters = lineCoordinates;
    }

    @Override
    public void execute() {
        Drawing line = line(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
        canvas.place(line);
    }
}
