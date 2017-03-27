package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.shapes.Drawing;

import java.util.List;

import static pl.bzawadka.drawing.shapes.Line.line;

public class DrawLineCommand implements Command {

    public static final CommandType commandType = CommandType.DRAW_LINE;
    public List<Integer> parameters;

    private final Invoker invoker;

    public DrawLineCommand(Invoker invoker, List<Integer> lineCoordinates) {
        this.invoker = invoker;
        this.parameters = lineCoordinates;
    }

    @Override
    public void execute() {
        Drawing line = line(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
        invoker.placeDrawing(line);
    }
}
