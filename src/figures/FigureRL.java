package figures;

import block.BlockColor;
import game.GameCoordinate;

import java.util.Set;

/**
 * Created by anton.
 */
public class FigureRL extends AbstractFigure {

    @Override
    protected Set<GameCoordinate> buildUpRotation(Builder builder) {
        return builder.right()
                      .down()
                      .down()
                      .left()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildDownRotation(Builder builder) {
        return builder.current()
                      .right()
                      .down()
                      .skip()
                      .left()
                      .down()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildRightRotation(Builder builder) {
        return builder.current()
                      .down()
                      .right()
                      .right()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildLeftRotation(Builder builder) {
        return builder.current()
                      .right()
                      .right()
                      .down()
                      .build();
    }

    @Override
    protected BlockColor getColor() {
        return BlockColor.ORANGE;
    }

    @Override
    public int getWidth(FigureRotation rotation) {
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

    @Override
    public int getHeight(FigureRotation rotation) {
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
}
