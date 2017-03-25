package pl.bzawadka.drawing.runner;

import pl.bzawadka.drawing.Canvas;
import pl.bzawadka.drawing.command.Command;
import pl.bzawadka.drawing.command.CommandFactory;
import pl.bzawadka.drawing.shapes.Point;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;
import static pl.bzawadka.drawing.shapes.Point.point;

public class Runner {
    private static final int DEFAULT_CANVAS_WIDTH = 20;
    private static final int DEFAULT_CANVAS_HEIGHT = 4;

    private final InputStream in;
    private final PrintStream out;

    private Canvas canvas;

    public Runner(InputStream in, PrintStream out) {
        requireNonNull(in, "InputStream must be provided");
        requireNonNull(out, "PrintStream must be provided");
        this.in = in;
        this.out = out;
        this.canvas = defaultCanvas();
    }

    public void run() {
        Scanner scanner = new Scanner(in);
        SimpleCommand simpleCommand;
        Command command;
        do {
            out.print("enter command: ");
            String line = scanner.nextLine();
            command = CommandFactory.parse(line, canvas);
            if (command != null) {
                command.execute();
            } else{
                simpleCommand = SimpleCommand.parse(line);
                List<Integer> args = simpleCommand.parameters;

                switch (simpleCommand.commandType) {
                    case CREATE_CANVAS:
                        canvas = Canvas.canvas(args.get(0), args.get(1));
                        break;
                    case BUCKET_FILL:
                        Point startingPoint = point(args.get(0), args.get(1));
                        canvas.bucketFill(startingPoint, simpleCommand.character.get());
                        break;
                    case QUIT:
                        break;
                }
            }
            out.println(canvas.draw());

        } while (!canvas.isComplete());
        scanner.close();
    }

    private Canvas defaultCanvas() {
        return Canvas.canvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
    }
}
