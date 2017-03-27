package pl.bzawadka.drawing.command;

import java.util.List;

public class CreateCanvasCommand implements Command {

    public static final CommandType commandType = CommandType.CREATE_CANVAS;
    public List<Integer> parameters;

    private final Invoker invoker;

    public CreateCanvasCommand(Invoker invoker, List<Integer> canvasDimentions) {
        this.invoker = invoker;
        this.parameters = canvasDimentions;
    }

    @Override
    public void execute() {
        invoker.initializeCanvas(parameters.get(0), parameters.get(1));
    }
}
