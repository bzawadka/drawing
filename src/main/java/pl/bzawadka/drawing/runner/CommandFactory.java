package pl.bzawadka.drawing.runner;

import pl.bzawadka.drawing.Canvas;

public class CommandFactory {

    public static Command parse(String input, Canvas canvas) {
        if ("q".equalsIgnoreCase(input))
            return new QuitCommand(canvas);

        return null; //throw new IllegalArgumentException("unrecognized command: " + input);
    }
}
