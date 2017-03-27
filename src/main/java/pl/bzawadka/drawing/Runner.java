package pl.bzawadka.drawing;

import pl.bzawadka.drawing.command.Command;
import pl.bzawadka.drawing.command.Invoker;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

public class Runner {
    private final InputStream in;
    private final PrintStream out;

    public Runner(InputStream in, PrintStream out) {
        requireNonNull(in, "InputStream must be provided");
        requireNonNull(out, "PrintStream must be provided");
        this.in = in;
        this.out = out;
    }

    public void run() {
        Scanner scanner = new Scanner(in);
        Invoker invoker = new Invoker();
        do {
            out.print("enter command: ");
            String line = scanner.nextLine();
            Command command = CommandFactory.parse(line, invoker);
            if (command != null)
                command.execute();
            out.println(invoker.drawCanvas());

        } while (!invoker.isCanvasComplete());
        scanner.close();
    }

}
