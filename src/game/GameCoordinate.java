package game;

import java.util.Objects;

/**
 * Created by anton.
 */
public class GameCoordinate implements Cloneable {
    private final int x;

    private final int y;

    public GameCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameCoordinate bottom() {
        return new GameCoordinate(x, y + 1);
    }

    public GameCoordinate top() {
        return new GameCoordinate(x, y - 1);
    }

    public GameCoordinate right() {
        return new GameCoordinate(x + 1, y);
    }

    public GameCoordinate left() {
        return new GameCoordinate(x - 1, y);
    }

    @Override
    public GameCoordinate clone() {
        return new GameCoordinate(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameCoordinate that = (GameCoordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
