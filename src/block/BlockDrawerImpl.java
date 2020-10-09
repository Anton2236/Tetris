package block;

import game.GameCoordinate;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

/**
 * Created by anton.
 */
public class BlockDrawerImpl implements BlockDrawer {

    @Override
    public void draw(Graphics g, Collection<GameBlock> gameBlocks) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        addRenderHints(graphics2D);
        for (GameBlock block : gameBlocks) {
            Shape shape = getShape(block);
            Paint paint = getPaint(block);
            graphics2D.setPaint(paint);
            graphics2D.fill(shape);
        }
    }

    @Override
    public void draw(Graphics g, Collection<GameBlock> gameBlocks, float alpha) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        addRenderHints(graphics2D);
        graphics2D.setComposite(AlphaComposite.SrcAtop.derive(alpha));
        for (GameBlock block : gameBlocks) {
            Shape shape = getShape(block);
            Paint paint = getPaint(block);
            graphics2D.setPaint(paint);
            graphics2D.fill(shape);
        }
    }

    private void addRenderHints(Graphics2D graphics2D) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHints(rh);
    }

    private Paint getPaint(GameBlock block) {
        BlockColor blockColor = block.getBlockColor();
        Color color = blockColor.getColor();
        GameCoordinate coordinate = block.getCoordinate();
        int x = coordinate.getX() * BLOCK_OUTLINE + PADDING;
        int y = coordinate.getY() * BLOCK_OUTLINE + PADDING;
        int size = BLOCK_OUTLINE - PADDING * 2;
        Point2D point1 = new Point2D.Double(x, y);
        Point2D point2 = new Point2D.Double(x + size, y + size);
        return new GradientPaint(point1,
                                 color.brighter()
                                      .brighter()
                                      .brighter(),
                                 point2,
                                 color.darker()
                                      .darker()
                                      .darker());
    }

    private Shape getShape(GameBlock block) {
        GameCoordinate coordinate = block.getCoordinate();
        int x = coordinate.getX() * BLOCK_OUTLINE + PADDING;
        int y = coordinate.getY() * BLOCK_OUTLINE + PADDING;
        int size = BLOCK_OUTLINE - PADDING * 2;
        return new Rectangle2D.Double(x, y, size, size);
    }

}
