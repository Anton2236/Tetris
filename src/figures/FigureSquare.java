package figures;

import block.BlockColor;
import game.GameCoordinate;

import java.util.Set;

/**
 * Created by anton.
 */
public class FigureSquare extends AbstractFigure {
    @Override
    protected Set<GameCoordinate> buildUpRotation(Builder builder) {
        return build(builder);
    }

    @Override
    protected Set<GameCoordinate> buildDownRotation(Builder builder) {
        return build(builder);
    }

    @Override
    protected Set<GameCoordinate> buildRightRotation(Builder builder) {
        return build(builder);
    }

    @Override
    protected Set<GameCoordinate> buildLeftRotation(Builder builder) {
        return build(builder);
    }

    private Set<GameCoordinate> build(Builder builder) {
        return builder.current()
                      .right()
                      .down()
                      .left()
                      .build();
    }

    @Override
    protected BlockColor getColor() {
        return BlockColor.YELLOW;
    }

    @Override
    public int getWidth(FigureRotation rotation) {
        return 2;
    }

    @Override
    public int getHeight(FigureRotation rotation) {
        return 2;
    }
}
