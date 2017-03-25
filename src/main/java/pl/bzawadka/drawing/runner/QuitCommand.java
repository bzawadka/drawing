package pl.bzawadka.drawing.runner;

import pl.bzawadka.drawing.Canvas;

public class QuitCommand implements Command {

    public static final CommandType commandType = CommandType.QUIT;

    private final Canvas canvas;

    public QuitCommand(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        canvas.complete();
    }
}
