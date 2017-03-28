package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Command;
import pl.bzawadka.drawing.Receiver;

public class QuitCommand implements Command {

    public static final CommandType commandType = CommandType.QUIT;

    private final Receiver receiver;

    public QuitCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.completeCanvas();
    }
}
