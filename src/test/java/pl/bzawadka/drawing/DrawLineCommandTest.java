package pl.bzawadka.drawing;

import com.google.common.collect.ImmutableList;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import pl.bzawadka.drawing.Canvas;
import pl.bzawadka.drawing.runner.DrawLineCommand;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.Canvas.canvas;
import static pl.bzawadka.drawing.runner.CommandType.DRAW_LINE;

public class DrawLineCommandTest {

    @Test
    public void quitCommandCanBeInstantiated() throws Exception {
        DrawLineCommand command = new DrawLineCommand(canvas(10, 10), ImmutableList.of(1, 2, 6, 2));
        assertThat(command.commandType).isEqualTo(DRAW_LINE);
    }

    @Test
    public void linesCanBeDrawnOnCanvas() {
        Canvas canvas = canvas(20, 4);
        DrawLineCommand command = new DrawLineCommand(canvas, ImmutableList.of(1, 2, 6, 2));
        command.execute();
        assertThat(canvas.draw()).isEqualTo(fileContent("canvas_20x4_with_1line.txt"));
    }

    private String fileContent(String fileName) {
        String content = null;
        try {
            URL resource = this.getClass().getResource(fileName);
            content = FileUtils.readFileToString(new File(resource.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return content;
    }


}