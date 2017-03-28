package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Canvas;
import pl.bzawadka.drawing.Command;

import java.util.List;

public class CreateCanvasCommand implements Command {

    public static final CommandType commandType = CommandType.CREATE_CANVAS;
    public List<Integer> parameters;

    private final Canvas canvas;

    public CreateCanvasCommand(Canvas canvas, List<Integer> canvasDimentions) {
        this.canvas = canvas;
        this.parameters = canvasDimentions;
    }

    @Override
    public void execute() {
        canvas.setSize(parameters.get(0), parameters.get(1));
    }
}
