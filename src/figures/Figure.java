package figures;

import block.GameBlock;
import game.GameCoordinate;

import java.util.Set;

/**
 * Created by anton.
 */
public interface Figure {
    int COUNT = 7;

    Set<GameBlock> getBlocks(GameCoordinate start, FigureRotation rotation);

    int getWidth(FigureRotation rotation);

    int getHeight(FigureRotation rotation);

    interface Builder {
        Figure.Builder up();

        Figure.Builder down();

        Figure.Builder right();

        Figure.Builder left();

        Figure.Builder current();

        Figure.Builder skip();

        Set<GameCoordinate> build();
    }
}
