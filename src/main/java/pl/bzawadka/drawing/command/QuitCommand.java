package pl.bzawadka.drawing.command;

public class QuitCommand implements Command {

    public static final CommandType commandType = CommandType.QUIT;

    private final Invoker invoker;

    public QuitCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void execute() {
        invoker.completeCanvas();
    }
}
