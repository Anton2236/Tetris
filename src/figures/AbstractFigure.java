package figures;

import block.BlockColor;
import block.GameBlock;
import game.GameCoordinate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by anton.
 */
public abstract class AbstractFigure implements Figure {
    @Override
    public Set<GameBlock> getBlocks(GameCoordinate start, FigureRotation rotation) {
        Set<GameCoordinate> coordinates;
        Figure.Builder builder = new FigureBuilder(start);
        switch (rotation) {
            case UP:
                coordinates = buildUpRotation(builder);
                break;
            case RIGHT:
                coordinates = buildRightRotation(builder);
                break;
            case DOWN:
                coordinates = buildDownRotation(builder);
                break;
            case LEFT:
                coordinates = buildLeftRotation(builder);
                break;
            default:
                coordinates = new HashSet<>();
        }
        BlockColor color = getColor();
        return coordinates.stream()
                          .map(c -> new GameBlock(c, color))
                          .collect(Collectors.toCollection(HashSet::new));
    }

    protected abstract Set<GameCoordinate> buildUpRotation(Figure.Builder builder);

    protected abstract Set<GameCoordinate> buildDownRotation(Figure.Builder builder);

    protected abstract Set<GameCoordinate> buildRightRotation(Figure.Builder builder);

    protected abstract Set<GameCoordinate> buildLeftRotation(Figure.Builder builder);

    protected abstract BlockColor getColor();

    private class FigureBuilder implements Figure.Builder {
        private GameCoordinate current;
        private Set<GameCoordinate> coordinates;

        private FigureBuilder(GameCoordinate current) {
            this.current = current;
            this.coordinates = new HashSet<>();
        }

        @Override
        public Builder up() {
            nextCoordinate(current.top());
            return this;
        }

        @Override
        public Builder down() {
            nextCoordinate(current.bottom());
            return this;
        }

        @Override
        public Builder right() {
            nextCoordinate(current.right());
            return this;
        }

        @Override
        public Builder left() {
            nextCoordinate(current.left());
            return this;
        }

        @Override
        public Builder current() {
            nextCoordinate(current.clone());
            return this;
        }

        private void nextCoordinate(GameCoordinate coordinate) {
            coordinates.add(coordinate);
            current = coordinate;
        }

        @Override
        public Set<GameCoordinate> build() {
            return new HashSet<>(coordinates);
        }

        @Override
        public Builder skip() {
            coordinates.remove(current);
            return this;
        }
    }
}
