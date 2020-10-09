package game;

import java.awt.*;

/**
 * Created by anton.
 */
public interface BoardDrawer {
    void draw(Graphics g, int width, int height, int stackHeight);

    void drawPause(Graphics g);

    void drawGameOver(Graphics g);
}
