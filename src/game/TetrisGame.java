package game;

import block.BlockDrawer;
import block.GameBlock;
import figures.FigureGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

/**
 * Created by anton.
 */
public class TetrisGame extends JPanel implements ActionListener {
    public static final int FIGURE_STACK_SIZE = 4;


    private static final int DELAY = 50;
    private static final int ANIMATION_TIME = 300;
    private GameBoard board;
    private BlockDrawer blockDrawer;
    private BoardDrawer boardDrawer;
    private Timer timer;
    private GameState gameState;

    private long currentDelay = 0;
    private final GameActionListener actionListener = new GameActionListener();

    private Set<GameBlock> cachedStackBlocks = null;
    private Set<GameBlock> deletedBlocks = null;

    public TetrisGame(FigureGenerator figureGenerator, BlockDrawer blockDrawer, BoardDrawer boardDrawer) {
        setFocusable(true);
        addKeyListener(actionListener);
        addFocusListener(actionListener);
        this.board = new GameBoard(figureGenerator);
        this.boardDrawer = boardDrawer;
        this.blockDrawer = blockDrawer;
        this.timer = new Timer(DELAY, this);
        startGame();
    }

    private void startGame() {
        gameState = GameState.MOVING;
        cachedStackBlocks = null;
        currentDelay = 0;
        board.clear();
        board.createNewFigure();
        timer.restart();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardDrawer.draw(g, GameBoard.BOARD_WIDTH, GameBoard.BOARD_HEIGHT, GameBoard.STACK_HEIGHT);
        switch (gameState) {
            case PAUSE:
            case MOVING:
                blockDrawer.draw(g, board.getStackBlocks());
                Set<GameBlock> figureBlocks = board.getFigureBlocks();
                if (figureBlocks != null) {
                    blockDrawer.draw(g, figureBlocks);
                }
                if (gameState == GameState.PAUSE) {
                    boardDrawer.drawPause(g);
                }
                break;
            case GAME_OVER:
                blockDrawer.draw(g, board.getStackBlocks());
                boardDrawer.drawGameOver(g);
                drawGameOver();
                break;
            case DELETING_LINES:
                if (cachedStackBlocks != null) {
                    blockDrawer.draw(g, cachedStackBlocks);
                }

                if (deletedBlocks != null) {
                    blockDrawer.draw(g, deletedBlocks, 1.0f - (float) (currentDelay) / (float) (ANIMATION_TIME));
                }
                break;
        }
    }


    private void drawGameOver() {
        timer.stop();
        System.out.println("Game Over");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameState state = gameState;
        switch (state) {
            case MOVING:
                if (processAction()) {
                    repaint();
                }
                break;
            case STOPPED:
                cachedStackBlocks = board.getStackBlocks();
                deletedBlocks = board.deleteLines();
                if (deletedBlocks != null) {
                    gameState = GameState.DELETING_LINES;
                    cachedStackBlocks.removeAll(deletedBlocks);
                }
                else {
                    gameState = GameState.NEXT_FIGURE;
                }
                currentDelay = 0;
                if (gameState == GameState.DELETING_LINES) {
                    repaint();
                }
                break;
            case NEXT_FIGURE:
                if (board.checkOverflow()) {
                    gameState = GameState.GAME_OVER;
                }
                else {
                    board.createNewFigure();
                    currentDelay = 0;
                    gameState = GameState.MOVING;
                }
                repaint();
                break;
            case DELETING_LINES:
                currentDelay += DELAY;
                if (currentDelay > ANIMATION_TIME) {
                    gameState = GameState.NEXT_FIGURE;
                    currentDelay = 0;
                }
                else {
                    repaint();
                }
                break;
        }
    }

    private boolean processAction() {
        currentDelay += DELAY;
        GameAction action = actionListener.getCurrentAction();
        if (action == null) {
            action = GameAction.DEFAULT;
        }

        if (currentDelay < action.getDelay()) {
            return false;
        }

        currentDelay = 0;
        switch (action) {
            case ROTATE:
                board.rotate();
                break;
            case MOVE_RIGHT:
                board.moveRight();
                break;
            case MOVE_LEFT:
                board.moveLeft();
                break;
            case MOVE_DOWN:
            case DEFAULT:
                if (!board.moveDown()) {
                    gameState = GameState.STOPPED;
                    return false;
                }
                break;
            case PAUSE:
                gameState = GameState.PAUSE;
                break;
        }
        return true;
    }

    private class GameActionListener extends KeyAdapter implements FocusListener {

        private GameAction currentAction;
        private GameAction lastAction;

        private GameActionListener() {
            this.currentAction = null;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    currentAction = GameAction.ROTATE;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    currentAction = GameAction.MOVE_DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    currentAction = GameAction.MOVE_LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    currentAction = GameAction.MOVE_RIGHT;
                    break;
                case KeyEvent.VK_SPACE:
                    startGame();
                    currentAction = null;
                    break;
                case KeyEvent.VK_P:
                    if (gameState == GameState.PAUSE) {
                        gameState = GameState.MOVING;
                        currentAction = null;
                        repaint();
                    }
                    else {
                        currentAction = GameAction.PAUSE;
                    }
                    break;
                default:
                    currentAction = null;
                    break;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (currentAction != null) {
                lastAction = currentAction;
                currentAction = null;
            }
        }

        public GameAction getCurrentAction() {
            GameAction action = currentAction;
            if (action == null) {
                action = lastAction;
                lastAction = null;
            }
            return action;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (gameState == GameState.PAUSE) {
                gameState = GameState.MOVING;
                currentAction = null;
                repaint();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            lastAction = GameAction.PAUSE;
        }
    }


    private enum GameAction {
        DEFAULT(600),
        ROTATE(200),
        MOVE_RIGHT(300),
        MOVE_LEFT(300),
        MOVE_DOWN(100),
        PAUSE(150);
        private final int delay;

        GameAction(int delay) {
            this.delay = delay;
        }

        public int getDelay() {
            return delay;
        }
    }

    private enum GameState {
        PAUSE,
        MOVING,
        STOPPED,
        DELETING_LINES,
        NEXT_FIGURE,
        GAME_OVER
    }
}
