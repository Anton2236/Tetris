package game;

import block.BlockDrawer;

import java.awt.*;

/**
 * Created by anton.
 */
public class BoardDrawerImpl implements BoardDrawer {

    @Override
    public void draw(Graphics g, int width, int height, int stackHeight) {
        Graphics2D g2d = (Graphics2D) g.create();
        int blockSize = BlockDrawer.BLOCK_OUTLINE;
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0, 0, width * blockSize, height * blockSize);
        g2d.setPaint(Color.WHITE);

        int y1 = 0;
        int y2 = BlockDrawer.BLOCK_OUTLINE * height;
        for (int w = 0; w <= width; w++) {
            int x = w * BlockDrawer.BLOCK_OUTLINE;
            g2d.drawLine(x, y1, x, y2);
        }

        int x1 = 0;
        int x2 = BlockDrawer.BLOCK_OUTLINE * width;
        for (int h = 0; h <= height; h++) {
            int y = h * BlockDrawer.BLOCK_OUTLINE;
            g2d.drawLine(x1, y, x2, y);
        }
        int y = (height - stackHeight) * BlockDrawer.BLOCK_OUTLINE;
        g2d.setPaint(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1, y, x2, y);
    }

    @Override
    public void drawPause(Graphics g) {

    }

    @Override
    public void drawGameOver(Graphics g) {

    }
}
