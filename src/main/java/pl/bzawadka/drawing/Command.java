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
    private static final String COMMAND_KEY_GROUP_NAME = "commandKey";
    private static Pattern COMMAND_PATTERN = Pattern.compile("(?<commandKey>[CLRB])[\\s\\d]*");

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
        char key = matcher.group(COMMAND_KEY_GROUP_NAME).charAt(0);
        return new Command(key, Optional.empty(), collectParameters(matcher));
    }

    private static List<Integer> collectParameters(Matcher matcher) {
        String commandArgs = matcher.group(0).substring(1);
        return Stream.of(commandArgs.split("\\s"))
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
