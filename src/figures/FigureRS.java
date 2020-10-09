package figures;

import block.BlockColor;
import game.GameCoordinate;

import java.util.Set;

/**
 * Created by anton.
 */
public class FigureRS extends AbstractFigure {
    @Override
    protected Set<GameCoordinate> buildUpRotation(Builder builder) {
        return builder.down()
                      .right()
                      .up()
                      .right()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildDownRotation(Builder builder) {
        return buildUpRotation(builder);
    }

    @Override
    protected Set<GameCoordinate> buildRightRotation(Builder builder) {
        return builder.current()
                      .down()
                      .right()
                      .down()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildLeftRotation(Builder builder) {
        return buildRightRotation(builder);
    }

    @Override
    protected BlockColor getColor() {
        return BlockColor.VIOLET;
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
