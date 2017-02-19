package pl.bzawadka.drawing;

import org.apache.commons.lang3.Validate;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
    private static Pattern COMMAND_PATTERN = Pattern.compile("[CLRB](\\s\\d*)*");

    public final char key;
    public final Optional<Character> character;
    public final int[] coordinates;

    public Command(char key, Optional<Character> character, int... coordinates) {
        this.key = key;
        this.character = character;
        this.coordinates = coordinates;
    }

    public static Command parse(String src) {
        Matcher matcher = COMMAND_PATTERN.matcher(src);
        Validate.isTrue(matcher.matches(), "Expected format of command is character followed by digits, separated by spaces, e.g. C 20 4");


        return null;
    }
}
