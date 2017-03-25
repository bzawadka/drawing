package pl.bzawadka.drawing.shapes;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;
import pl.bzawadka.drawing.runner.SimpleCommand;
import pl.bzawadka.drawing.runner.CommandType;

import java.util.List;
import java.util.Set;

import static pl.bzawadka.drawing.runner.CommandType.DRAW_LINE;
import static pl.bzawadka.drawing.runner.CommandType.DRAW_RECTANGLE;
import static pl.bzawadka.drawing.shapes.Line.line;
import static pl.bzawadka.drawing.shapes.Rectangle.rectangle;

public class DrawingFactory {
    private static final Set<CommandType> supportedCommands = ImmutableSet.of(DRAW_LINE, DRAW_RECTANGLE);

    public static Drawing newDrawing(SimpleCommand command) {
        CommandType type = command.commandType;
        Validate.isTrue(supportedCommands.contains(type), "Unsupported Command: " + type);
        List<Integer> args = command.parameters;

        switch (type) {
            case DRAW_LINE:
                return line(args.get(0), args.get(1), args.get(2), args.get(3));

            case DRAW_RECTANGLE:
                return rectangle(args.get(0), args.get(1), args.get(2), args.get(3));

            default:
                throw new NotImplementedException("Type not supported: " + type);
        }
    }
}
