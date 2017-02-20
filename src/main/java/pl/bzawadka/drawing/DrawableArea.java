package pl.bzawadka.drawing;

import pl.bzawadka.drawing.shapes.Drawing;

public interface DrawableArea {

    String draw();

    void place(Drawing drawing);
}
