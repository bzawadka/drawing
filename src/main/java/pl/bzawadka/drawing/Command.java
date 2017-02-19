package pl.bzawadka.drawing;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Command {
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
        Matcher matcher = COMMAND_PATTERN.matcher(src);
        Validate.isTrue(matcher.matches(), "Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");
        char key = matcher.group("commandKey").charAt(0);

        String commandArgs = matcher.group(0).substring(1);
        List<Integer> parameters = Stream.of(commandArgs.split("\\s"))
                .filter(s -> !s.isEmpty())
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        return new Command(key, Optional.empty(), parameters);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
