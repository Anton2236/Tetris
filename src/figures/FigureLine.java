package figures;

import block.BlockColor;
import game.GameCoordinate;

import java.util.Set;

/**
 * Created by anton.
 */
public class FigureLine extends AbstractFigure {
    @Override
    protected Set<GameCoordinate> buildUpRotation(Builder builder) {
        return builder.current()
                      .down()
                      .down()
                      .down()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildDownRotation(Builder builder) {
        return buildUpRotation(builder);
    }

    @Override
    protected Set<GameCoordinate> buildRightRotation(Builder builder) {
        return builder.current()
                      .right()
                      .right()
                      .right()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildLeftRotation(Builder builder) {
        return buildRightRotation(builder);
    }

    @Override
    protected BlockColor getColor() {
        return BlockColor.RED;
    }

    @Override
    public int getWidth(FigureRotation rotation) {
        switch (rotation) {
            case UP:
            case DOWN:
            default:
                return 1;
            case RIGHT:
            case LEFT:
                return 4;
        }
    }

    @Override
    public int getHeight(FigureRotation rotation) {
        switch (rotation) {
            case UP:
            case DOWN:
            default:
                return 4;
            case RIGHT:
            case LEFT:
                return 1;
        }
    }
}
