package pl.bzawadka.drawing.runner;

import org.apache.commons.lang3.Validate;
import pl.bzawadka.drawing.Canvas;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.bzawadka.drawing.runner.CommandType.QUIT;

public class CommandFactory {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("(?<commandKey>[CLRBclrb])(?<numbers>[\\s\\d]*)(?<character>[a-z])?");
    private static final String GROUP_NAME_COMMAND_KEY = "commandKey";
    private static final String GROUP_NAME_NUMBERS = "numbers";
    private static final String GROUP_NAME_CHARACTER = "character";

    public static Command parse(String input, Canvas canvas) {
        if (QUIT.getCode().toString().equalsIgnoreCase(input))
            return new QuitCommand(canvas);

        if (input.startsWith("l") || input.startsWith("L")) {
            Matcher matcher = COMMAND_PATTERN.matcher(input);
            Validate.isTrue(matcher.matches(), "Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
            List<Integer> parameters = collectParameters(matcher);
            return new DrawLineCommand(canvas, parameters);
        }

        return null; //throw new IllegalArgumentException("unrecognized command: " + input);
    }

    private static List<Integer> collectParameters(Matcher matcher) {
        String numbersGroup = matcher.group(GROUP_NAME_NUMBERS);
        return Stream.of(numbersGroup.split("\\s"))
                .filter(s -> !s.isEmpty())
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
