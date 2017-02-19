package pl.bzawadka.drawing;

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
        String line;
        do {
            out.print("enter command: ");
            line = scanner.nextLine();
            out.println(new Canvas(1,1).draw());
        } while (!"Q".equalsIgnoreCase(line));
        scanner.close();
    }
}
