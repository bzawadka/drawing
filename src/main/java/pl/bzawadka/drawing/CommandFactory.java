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
        if (CommandType.QUIT.getCode().toString().equalsIgnoreCase(input))
            return new QuitCommand(canvas);

        if (input.startsWith("l") || input.startsWith("L")) {
            return new DrawLineCommand(canvas, collectParameters(input));
        }

        if (input.startsWith("r") || input.startsWith("R")) {
            return new DrawRectangleCommand(canvas, collectParameters(input));
        }

        if (input.startsWith("b") || input.startsWith("B")) {
            return new BucketFillCommand(canvas, collectParameters(input), collectCharacter(input));
        }

        return null; //throw new IllegalArgumentException("unrecognized command: " + input);
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
            throw new IllegalStateException("I'm puzzled, this should never happen (but it will)");
        }
        return group.charAt(0);
    }

    private static Matcher getMatcher(String input) {
        Matcher matcher = COMMAND_PATTERN.matcher(input);
        Validate.isTrue(matcher.matches(), "Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
        return matcher;
    }

}
