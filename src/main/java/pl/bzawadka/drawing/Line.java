package pl.bzawadka.drawing;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;

import java.util.Set;

public class Line implements Drawing {
    public final int x1;
    public final int y1;
    public final int x2;
    public final int y2;

    public Line(int x1, int y1, int x2, int y2) {
        Validate.isTrue(x1 == x2 || y1 == y2, "Line must be vertical or horizontal");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public Set<Point> getPoints() {
        throw new NotImplementedException("not implemented yet");
    }
}
