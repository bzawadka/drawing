package pl.bzawadka.drawing.runner;

import pl.bzawadka.drawing.Canvas;

import static pl.bzawadka.drawing.runner.CommandType.QUIT;

public class CommandFactory {

    public static Command parse(String input, Canvas canvas) {
        if (QUIT.getCode().toString().equalsIgnoreCase(input))
            return new QuitCommand(canvas);

        return null; //throw new IllegalArgumentException("unrecognized command: " + input);
    }
}
