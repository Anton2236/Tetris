package figures;

import block.BlockColor;
import game.GameCoordinate;

import java.util.Set;

/**
 * Created by anton.
 */
public class FigureT extends AbstractFigure {
    @Override
    protected Set<GameCoordinate> buildUpRotation(Builder builder) {
        return builder.down()
                      .right()
                      .up()
                      .right()
                      .skip()
                      .down()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildDownRotation(Builder builder) {
        return builder.current()
                      .right()
                      .right()
                      .down()
                      .skip()
                      .left()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildRightRotation(Builder builder) {
        return builder.current()
                      .right()
                      .skip()
                      .down()
                      .left()
                      .down()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildLeftRotation(Builder builder) {
        return builder.right()
                      .down()
                      .left()
                      .down()
                      .skip()
                      .right()
                      .build();
    }

    @Override
    protected BlockColor getColor() {
        return BlockColor.PINK;
    }

    @Override
    public int getWidth(FigureRotation rotation) {
        switch (rotation) {
            case UP:
            case DOWN:
            default:
                return 3;
            case RIGHT:
            case LEFT:
                return 2;
        }
    }

    @Override
    public int getHeight(FigureRotation rotation) {
        switch (rotation) {
            case UP:
            case DOWN:
            default:
                return 2;
            case RIGHT:
            case LEFT:
                return 3;
        }
    }
}
