package pl.bzawadka.drawing.command;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import pl.bzawadka.drawing.Receiver;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bzawadka.drawing.FileUtils.fileContent;
import static pl.bzawadka.drawing.command.CommandType.BUCKET_FILL;

public class BucketFillCommandTest {

    @Test
    public void bucketFillCommandCanBeInstantiated() throws Exception {
        BucketFillCommand command = new BucketFillCommand(new Receiver(), ImmutableList.of(1, 2), 'z');
        assertThat(command.commandType).isEqualTo(BUCKET_FILL);
        assertThat(command.parameters).containsOnly(1, 2);
        assertThat(command.character).isEqualTo('z');
    }

    @Test
    public void executeFillsBucketOnCanvas() {
        Receiver receiver = new Receiver();
        receiver.initializeCanvas(12, 4);

        new DrawRectangleCommand(receiver, ImmutableList.of(2, 2, 5, 4)).execute();
        assertThat(receiver.drawCanvas()).isEqualTo(fileContent("canvas_10x4_with_1rectangle.txt"));

        new BucketFillCommand(receiver, ImmutableList.of(3, 3), 'Z').execute();
        assertThat(receiver.drawCanvas()).isEqualTo(fileContent("canvas_10x4_with_1rectangle_filled.txt"));
    }

}