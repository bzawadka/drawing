package pl.bzawadka.drawing;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;

public class RunnerTest {

    @Test
    public void constructorParametersMustBeProvided() {
        PrintStream out = new PrintStream(new ByteArrayOutputStream());
        assertThatThrownBy(() -> new Runner(null, out))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("InputStream must be provided");

        InputStream in = new ByteArrayInputStream(("Q").getBytes());
        assertThatThrownBy(() -> new Runner(in, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("PrintStream must be provided");
    }

    @Test
    public void runnerTerminatesWhenTerminatingCharacterIsProvided() {
        String terminatingCharacter = "Q";
        InputStream in = new ByteArrayInputStream(terminatingCharacter.getBytes());
        PrintStream out = new PrintStream(new ByteArrayOutputStream());
        new Runner(in, out).run();
    }

}