package pl.bzawadka.drawing;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

/**
 * An invoker object knows how to execute a {@link Command}. The invoker does not
 * know anything about a concrete command, it knows only about command interface.
 */
public class Runner {
    private final InputStream in;
    private final PrintStream out;
    private Receiver receiver;

    public Runner(InputStream in, PrintStream out) {
        requireNonNull(in, "InputStream must be provided");
        requireNonNull(out, "PrintStream must be provided");
        this.in = in;
        this.out = out;
        this.receiver = new Receiver();
    }

    public void invokeCommands() {
        Scanner scanner = new Scanner(in);
        do {
            out.print("enter command: ");

            String line = scanner.nextLine();
            Command command = CommandFactory.parse(line, receiver);
            if (command != null)
                command.execute();

            out.println(receiver.drawCanvas());

        } while (!receiver.isCanvasComplete());
        scanner.close();
    }

}
