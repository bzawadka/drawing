package pl.bzawadka.drawing.shapes;

import org.junit.Test;
import pl.bzawadka.drawing.runner.SimpleCommand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static pl.bzawadka.drawing.shapes.Point.point;

public class DrawingFactoryTest {

    @Test
    public void onlySelectedCommandsAreDrawings() {
        SimpleCommand unsupportedCommand = SimpleCommand.parse("C 20 4");
        assertThatThrownBy(() -> DrawingFactory.newDrawing(unsupportedCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported Command: CREATE_CANVAS");
    }

    @Test
    public void lineIsParsedFromCommand() {
        SimpleCommand command1 = SimpleCommand.parse("L 1 2 3 2");
        Drawing horizontalLine = DrawingFactory.newDrawing(command1);
        assertThat(horizontalLine).isExactlyInstanceOf(Line.class);
        assertThat(horizontalLine.getPoints()).containsOnly(point(1, 2), point(2, 2), point(3, 2));

        SimpleCommand command2 = SimpleCommand.parse("L 4 1 4 3");
        Drawing verticalLine = DrawingFactory.newDrawing(command2);
        assertThat(verticalLine).isExactlyInstanceOf(Line.class);
        assertThat(verticalLine.getPoints()).containsOnly(point(4, 1), point(4, 2), point(4, 3));
    }

    @Test
    public void rectangleIsParsedFromCommand() {
        SimpleCommand command = SimpleCommand.parse("R 14 1 18 3");
        Drawing rectangle = DrawingFactory.newDrawing(command);
        assertThat(rectangle).isExactlyInstanceOf(Rectangle.class);
        assertThat(rectangle.getPoints())
                .containsOnly(
                        point(14, 1), point(15, 1), point(16, 1), point(17, 1), point(18, 1),
                        point(14, 2), point(18, 2),
                        point(14, 3), point(15, 3), point(16, 3), point(17, 3), point(18, 3));

    }

}