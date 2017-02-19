package pl.bzawadka.drawing;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;

import static pl.bzawadka.drawing.CommandType.DRAW_LINE;
import static pl.bzawadka.drawing.CommandType.DRAW_RECTANGLE;

public class DrawingFactory {
    private static final Set<CommandType> supportedCommands = ImmutableSet.of(DRAW_LINE, DRAW_RECTANGLE);

    public static Drawing createInstance(Command command) {
        CommandType type = command.commandType;
        Validate.isTrue(supportedCommands.contains(type), "Unsupported Command: " + type);
        List<Integer> args = command.parameters;

        switch (type) {
            case DRAW_LINE:
                return new Line(args.get(0), args.get(1), args.get(2), args.get(3));

            case DRAW_RECTANGLE:
            default:
                throw new NotImplementedException("wait for Rectangle class"); // TODO implement
        }
    }
}
