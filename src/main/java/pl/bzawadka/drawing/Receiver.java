package pl.bzawadka.drawing;

import pl.bzawadka.drawing.shapes.Drawing;
import pl.bzawadka.drawing.shapes.Point;

/**
 * A {@link Command} object knows about {@link Receiver} and invokes a method of the receiver.
 * Values for parameters of the receiver method are stored in the command.
 * The receiver then does the work.
 */
public class Receiver {
    private Canvas canvas = Canvas.canvas();

    public void initializeCanvas(int width, int height) {
        canvas.setSize(width, height);
    }

    public void placeDrawing(Drawing drawing) {
        canvas.place(drawing);
    }

    public void canvasBucketFill(Point startingPoint, char character) {
        canvas.bucketFill(startingPoint, character);
    }

    public String drawCanvas() {
        return canvas.draw();
    }

    public void completeCanvas() {
        canvas.complete();
    }

    public boolean isCanvasComplete() {
        return canvas.isComplete();
    }
}
