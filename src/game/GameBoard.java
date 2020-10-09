package game;

import block.GameBlock;
import figures.Figure;
import figures.FigureGenerator;
import figures.FigureRotation;

import java.util.*;

/**
 * Created by anton.
 */
public class GameBoard {
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 19;
    public static final int STACK_HEIGHT = BOARD_HEIGHT - 4;


    private Figure currentFigure = null;
    private FigureRotation figureRotation = null;
    private GameCoordinate figureCoordinate = null;
    private final Set<GameBlock> blocks = new HashSet<>();

    private FigureGenerator figureGenerator;

    public GameBoard(FigureGenerator figureGenerator) {
        this.figureGenerator = figureGenerator;
    }

    public void createNewFigure() {
        if (currentFigure != null) {
            return;
        }
        currentFigure = figureGenerator.pollFigure();
        figureRotation = randomRotation();
        figureCoordinate = new GameCoordinate((BOARD_WIDTH - currentFigure.getWidth(figureRotation)) / 2 - 1, 0);
    }

    public void clear() {
        blocks.clear();
        currentFigure = null;
    }

    public boolean moveDown() {
        GameCoordinate newCoordinate = figureCoordinate.bottom();
        if (checkTransformation(newCoordinate, figureRotation)) {
            figureCoordinate = newCoordinate;
            return true;
        }
        else {
            Set<GameBlock> figureBlocks = currentFigure.getBlocks(figureCoordinate, figureRotation);
            blocks.addAll(figureBlocks);
            currentFigure = null;
            return false;
        }
    }

    public void rotate() {
        FigureRotation newRotation = FigureRotation.getNext(figureRotation);
        if (checkTransformation(figureCoordinate, newRotation)) {
            figureRotation = newRotation;
        }
    }

    public void moveRight() {
        GameCoordinate newCoordinate = figureCoordinate.right();
        if (checkTransformation(newCoordinate, figureRotation)) {
            figureCoordinate = newCoordinate;
        }
    }

    public void moveLeft() {
        GameCoordinate newCoordinate = figureCoordinate.left();
        if (checkTransformation(newCoordinate, figureRotation)) {
            figureCoordinate = newCoordinate;
        }
    }

    private boolean checkTransformation(GameCoordinate newCoordinate, FigureRotation newRotation) {
        Set<GameBlock> figureBlocks = currentFigure.getBlocks(newCoordinate, newRotation);
        boolean success = true;
        for (GameBlock gameBlock : figureBlocks) {
            GameCoordinate coordinate = gameBlock.getCoordinate();

            if (coordinate.getY() >= BOARD_HEIGHT) {
                success = false;
                break;
            }

            if (coordinate.getX() < 0) {
                success = false;
                break;
            }
            if (coordinate.getX() >= BOARD_WIDTH) {
                success = false;
                break;
            }


            if (blocks.contains(gameBlock)) {
                success = false;
                break;
            }
        }
        return success;
    }

    public Set<GameBlock> deleteLines() {
        Map<Integer, Set<GameBlock>> lines = new HashMap<>();
        int maxLine = 0;
        for (GameBlock block : blocks) {
            GameCoordinate coordinate = block.getCoordinate();
            int y = coordinate.getY();
            int lineInd = BOARD_HEIGHT - y - 1;
            if (!lines.containsKey(lineInd)) {
                lines.put(lineInd, new HashSet<>());
            }
            Set<GameBlock> line = lines.get(lineInd);
            line.add(block);
            maxLine = Math.max(maxLine, lineInd);
        }
        Set<Integer> toDelete = new HashSet<>();
        for (int lineInd = 0; lineInd <= maxLine; lineInd++) {
            Set<GameBlock> line = lines.get(lineInd);
            if (line == null) {
                continue;
            }
            if (line.size() >= BOARD_WIDTH) {
                toDelete.add(lineInd);
            }
        }
        if (toDelete.size() <= 0) {
            return null;
        }
        blocks.clear();
        int y = BOARD_HEIGHT - 1;
        for (int lineInd = 0; lineInd <= maxLine; lineInd++) {
            if (toDelete.contains(lineInd)) {
                continue;
            }
            Set<GameBlock> line = lines.get(lineInd);
            for (GameBlock block : line) {
                GameCoordinate blockCoordinate = block.getCoordinate();
                GameCoordinate newCoordinate = new GameCoordinate(blockCoordinate.getX(), y);
                GameBlock newBlock = new GameBlock(newCoordinate, block.getBlockColor());
                blocks.add(newBlock);
            }
            y--;
        }
        return toDelete.stream()
                       .map(lines::get)
                       .reduce(new HashSet<>(), (s1, s2) -> {
                           s1.addAll(s2);
                           return s1;
                       });
    }

    public boolean checkOverflow() {
        for (GameBlock gameBlock : blocks) {
            GameCoordinate coordinate = gameBlock.getCoordinate();
            if (coordinate.getY() < BOARD_HEIGHT - STACK_HEIGHT) {
                return true;
            }
        }
        return false;
    }

    public Set<GameBlock> getStackBlocks() {
        return new HashSet<>(blocks);
    }

    public Set<GameBlock> getFigureBlocks() {
        Set<GameBlock> blocks = null;
        if (currentFigure != null) {
            blocks = currentFigure.getBlocks(figureCoordinate, figureRotation);
        }
        return blocks;
    }

    private FigureRotation randomRotation() {
        Random random = new Random();
        FigureRotation[] figureRotations = FigureRotation.values();
        return figureRotations[random.nextInt(figureRotations.length)];
    }
}
