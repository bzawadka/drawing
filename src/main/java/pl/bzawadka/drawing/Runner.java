package pl.bzawadka.drawing;

import pl.bzawadka.drawing.command.Client;
import pl.bzawadka.drawing.command.CommandFactory;
import pl.bzawadka.drawing.command.Invoker;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

@Invoker
@Client
public class Runner {
    private final InputStream in;
    private final PrintStream out;
    private Canvas canvas;

    public Runner(InputStream in, PrintStream out) {
        requireNonNull(in, "InputStream must be provided");
        requireNonNull(out, "PrintStream must be provided");
        this.in = in;
        this.out = out;
        this.canvas = Canvas.canvas();
    }

    public void invokeCommands() {
        Scanner scanner = new Scanner(in);
        do {
            out.print("enter command: ");
            String line = scanner.nextLine();

            Command command = CommandFactory.parse(line, canvas);
            command.execute();

            out.println(canvas.draw());

        } while (!canvas.isComplete());
        scanner.close();
    }

}
