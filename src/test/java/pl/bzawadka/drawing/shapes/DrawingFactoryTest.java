package pl.bzawadka.drawing.shapes;

import org.junit.Test;
import pl.bzawadka.drawing.runner.Command;

import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.shapes.DrawingAssert.assertThat;
import static pl.bzawadka.drawing.shapes.Point.point;

public class DrawingFactoryTest {

    @Test
    public void onlySelectedCommandsAreDrawings() {
        Command unsupportedCommand = Command.parse("C 20 4");
        assertThatThrownBy(() -> DrawingFactory.createInstance(unsupportedCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported Command: CREATE_CANVAS");
    }

    @Test
    public void lineIsParsedFromCommand() {
        Command command1 = Command.parse("L 1 2 3 2");
        Drawing horizontalLine = DrawingFactory.createInstance(command1);
        assertThat(horizontalLine).isExactlyInstanceOf(Line.class);
        assertThat(horizontalLine).hasOnlyPoints(point(1, 2), point(2, 2), point(3, 2));

        Command command2 = Command.parse("L 4 1 4 3");
        Drawing verticalLine = DrawingFactory.createInstance(command2);
        assertThat(verticalLine).isExactlyInstanceOf(Line.class);
        assertThat(verticalLine).hasOnlyPoints(point(4, 1), point(4, 2), point(4, 3));
    }

}