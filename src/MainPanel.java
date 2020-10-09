import block.BlockDrawerImpl;
import figures.FigureGenerator;
import game.BoardDrawerImpl;
import game.TetrisGame;

import javax.swing.*;

/**
 * Created by anton.
 */
public class MainPanel extends JFrame {
    public static final String TITLE = "Tetris";
    private TetrisGame tetrisGame;

    public MainPanel() {

        initUI();
    }

    private void initUI() {
        tetrisGame = new TetrisGame(new FigureGenerator(TetrisGame.FIGURE_STACK_SIZE),
                                    new BlockDrawerImpl(),
                                    new BoardDrawerImpl());
        add(tetrisGame);
        setTitle(TITLE);
        setSize(700, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
