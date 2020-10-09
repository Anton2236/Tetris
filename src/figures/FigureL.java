package figures;

import block.BlockColor;
import game.GameCoordinate;

import java.util.Set;

/**
 * Created by anton.
 */
public class FigureL extends AbstractFigure {

    @Override
    protected Set<GameCoordinate> buildUpRotation(Builder builder) {
        return builder.current()
                      .down()
                      .down()
                      .right()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildDownRotation(Builder builder) {
        return builder.current()
                      .right()
                      .down()
                      .down()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildRightRotation(Builder builder) {
        return builder.current()
                      .down()
                      .right()
                      .skip()
                      .up()
                      .right()
                      .build();
    }

    @Override
    protected Set<GameCoordinate> buildLeftRotation(Builder builder) {
        return builder.down()
                      .right()
                      .right()
                      .up()
                      .build();
    }

    @Override
    protected BlockColor getColor() {
        return BlockColor.GREEN;
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
