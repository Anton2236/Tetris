package block;

import game.GameCoordinate;

import java.util.Objects;

/**
 * Created by anton.
 */
public class GameBlock {
    private final GameCoordinate coordinate;

    private final BlockColor color;

    public GameBlock(GameCoordinate coordinate, BlockColor color) {
        this.coordinate = coordinate;
        this.color = color;
    }

    public GameCoordinate getCoordinate() {
        return coordinate;
    }

    public BlockColor getBlockColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameBlock gameBlock = (GameBlock) o;
        return Objects.equals(coordinate, gameBlock.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
