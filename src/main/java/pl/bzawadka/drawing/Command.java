package pl.bzawadka.drawing;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.bzawadka.drawing.CommandType.QUIT;

public class Command {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("(?<commandKey>[CLRBclrb])(?<numbers>[\\s\\d]*)(?<character>[a-z])?");
    private static final String GROUP_NAME_COMMAND_KEY = "commandKey";
    private static final String GROUP_NAME_NUMBERS = "numbers";
    private static final String GROUP_NAME_CHARACTER = "character";

    public final CommandType commandType;
    public final char key;
    public final Optional<Character> character;
    public final List<Integer> parameters;

    public Command(char key, Optional<Character> character, List<Integer> parameters) {
        this.key = key;
        this.commandType = CommandType.parseFrom(key);
        this.character = character;
        this.parameters = parameters;
    }

    public static Command parse(String src) {
        return isQuitCommand(src) ? quitCommand() : parametrizedCommand(src);
    }

    private static Command parametrizedCommand(String src) {
        Matcher matcher = COMMAND_PATTERN.matcher(src);
        Validate.isTrue(matcher.matches(), "Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
        return new Command(collectKey(matcher), collectCharacter(matcher), collectParameters(matcher));
    }

    private static char collectKey(Matcher matcher) {
        return matcher.group(GROUP_NAME_COMMAND_KEY).toUpperCase().charAt(0);
    }

    private static Optional<Character> collectCharacter(Matcher matcher) {
        String optionalGroup = matcher.group(GROUP_NAME_CHARACTER);
        if (optionalGroup == null) {
            return Optional.empty();
        }
        return Optional.of(optionalGroup.charAt(0));
    }

    private static List<Integer> collectParameters(Matcher matcher) {
        String numbersGroup = matcher.group(GROUP_NAME_NUMBERS);
        return Stream.of(numbersGroup.split("\\s"))
                .filter(s -> !s.isEmpty())
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static boolean isQuitCommand(String src) {
        return QUIT.getCode().toString().equalsIgnoreCase(src);
    }

    private static Command quitCommand() {
        return new Command(QUIT.getCode().charValue(), Optional.empty(), ImmutableList.of());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
