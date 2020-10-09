package figures;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by anton.
 */
public class FigureGenerator {
    private LinkedList<Figure> figures;

    public FigureGenerator(int figureCount) {
        figures = new LinkedList<>();
        for (int i = 0; i < figureCount; i++) {
            figures.add(randomFigure());
        }
    }

    public Figure pollFigure() {
        figures.add(randomFigure());
        return figures.pollFirst();
    }

    private Figure randomFigure() {
        Random random = new Random();
        switch (random.nextInt(Figure.COUNT)) {
            case 0:
            default:
                return new FigureL();
            case 1:
                return new FigureRL();
            case 2:
                return new FigureS();
            case 3:
                return new FigureRS();
            case 4:
                return new FigureLine();
            case 5:
                return new FigureSquare();
            case 6:
                return new FigureT();
        }
    }
}
