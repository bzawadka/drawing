package pl.bzawadka.drawing;

import org.apache.commons.lang3.Validate;

import java.util.Set;

public class Rectangle implements Drawing {

    public Rectangle(int x1, int y1, int x2, int y2) {
        Validate.isTrue(x1 != x2 && y1 != y2, "Points must not be in one line");
    }

    @Override
    public Set<Point> getPoints() {
        return null;
    }

    @Override
    public char getCharacter() {
        return 0;
    }

    public static Rectangle rectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle(x1, y1, x2, y2);
    }
}
