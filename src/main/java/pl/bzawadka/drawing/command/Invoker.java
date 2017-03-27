package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Canvas;
import pl.bzawadka.drawing.shapes.Drawing;
import pl.bzawadka.drawing.shapes.Point;

import static pl.bzawadka.drawing.Canvas.canvas;

public class Invoker {
    private Canvas canvas;

    public void initializeCanvas(int width, int height) {
        canvas = canvas(width, height);
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
