package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Command;
import pl.bzawadka.drawing.Receiver;

import java.util.List;

public class CreateCanvasCommand implements Command {

    public static final CommandType commandType = CommandType.CREATE_CANVAS;
    public List<Integer> parameters;

    private final Receiver receiver;

    public CreateCanvasCommand(Receiver receiver, List<Integer> canvasDimentions) {
        this.receiver = receiver;
        this.parameters = canvasDimentions;
    }

    @Override
    public void execute() {
        receiver.initializeCanvas(parameters.get(0), parameters.get(1));
    }
}
