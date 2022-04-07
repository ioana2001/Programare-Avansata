package component;

import java.io.Serializable;
import java.util.Objects;

/**
 * x1, y1 represents the coordinates for the start node for the stick and
 * x2, y2 represents the coordinates for the end node for the stick
 */
public class Stick implements Serializable {
    private int x1, y1, x2, y2;

    public Stick(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return "{" + "\"x1\":\"" + x1 + "\", \"y1\":\"" + y1
                + "\", \"x2\":\"" + x2 + "\", \"y2\":\"" + y2 + "\"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stick stick = (Stick) o;
        return (x1 == stick.x1 && y1 == stick.y1 && x2 == stick.x2 && y2 == stick.y2) ||
                (x1 == stick.x2 && y1 == stick.y2 && x2 == stick.x1 && y2 == stick.y1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
