package pl.bzawadka.drawing;

import org.apache.commons.lang3.Validate;
import pl.bzawadka.drawing.command.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandFactory {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("(?<commandKey>[CLRBclrb])(?<numbers>[\\s\\d]*)(?<character>[a-z])?");
    private static final String GROUP_NAME_COMMAND_KEY = "commandKey";
    private static final String GROUP_NAME_NUMBERS = "numbers";
    private static final String GROUP_NAME_CHARACTER = "character";

    public static Command parse(String input, Canvas canvas) {
        if (startsWithCommandCode(input, CommandType.QUIT))
            return new QuitCommand(canvas);

        if (startsWithCommandCode(input, CommandType.CREATE_CANVAS)) {
            return new CreateCanvasCommand(canvas, collectParameters(input));
        }

        if (startsWithCommandCode(input, CommandType.DRAW_LINE)) {
            return new DrawLineCommand(canvas, collectParameters(input));
        }

        if (startsWithCommandCode(input, CommandType.DRAW_RECTANGLE)) {
            return new DrawRectangleCommand(canvas, collectParameters(input));
        }

        if (startsWithCommandCode(input, CommandType.BUCKET_FILL)) {
            return new BucketFillCommand(canvas, collectParameters(input), collectCharacter(input));
        }

        throw new IllegalArgumentException("unrecognized command: " + input);
    }

    private static boolean startsWithCommandCode(String input, CommandType commandType) {
        String code = commandType.getCode().toString();
        return input.startsWith(code.toLowerCase()) ||
                input.startsWith(code.toUpperCase());
    }

    private static List<Integer> collectParameters(String input) {
        Matcher matcher = getMatcher(input);
        String numbersGroup = matcher.group(GROUP_NAME_NUMBERS);
        return Stream.of(numbersGroup.split("\\s"))
                .filter(s -> !s.isEmpty())
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static Character collectCharacter(String input) {
        Matcher matcher = getMatcher(input);
        String group = matcher.group(GROUP_NAME_CHARACTER);
        if (group == null) {
            throw new IllegalStateException("character expected as part of command");
        }
        return group.charAt(0);
    }

    private static Matcher getMatcher(String input) {
        Matcher matcher = COMMAND_PATTERN.matcher(input);
        Validate.isTrue(matcher.matches(), "Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
        return matcher;
    }

}
