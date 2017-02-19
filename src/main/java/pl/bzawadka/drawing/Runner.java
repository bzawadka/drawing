package pl.bzawadka.drawing;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;
import static pl.bzawadka.drawing.Line.line;
import static pl.bzawadka.drawing.Rectangle.rectangle;

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
        String inputLine;
        Canvas canvas = defaultCanvas();

        do {
            out.print("enter command: ");

            inputLine = scanner.nextLine();

            Command command = Command.parse(inputLine);
            List<Integer> args = command.parameters;

            switch (command.commandType) {
                case CREATE_CANVAS:
                    canvas = new Canvas(args.get(0), args.get(1));
                    break;
                case DRAW_LINE:
                    Drawing line = line(args.get(0), args.get(1), args.get(2), args.get(3));
                    canvas.place(line);
                    break;
                case DRAW_RECTANGLE:
                    Drawing rectangle = rectangle(args.get(0), args.get(1), args.get(2), args.get(3));
                    canvas.place(rectangle);
                    break;
                case BUCKET_FILL:
                    break;
                case QUIT:
                    System.out.println("actually, I could quit as command");
                    break;
            }

            out.println(canvas.draw());

        } while (!"Q".equalsIgnoreCase(inputLine));
        scanner.close();
    }

    private Canvas defaultCanvas() {
        return new Canvas(20, 4);
    }
}
