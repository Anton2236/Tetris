package block;

import java.awt.*;
import java.util.Collection;

/**
 * Created by anton.
 */
public interface BlockDrawer {
    int BLOCK_OUTLINE = 45;
    int PADDING = 2;

    void draw(Graphics g, Collection<GameBlock> gameBlocks);


    void draw(Graphics g, Collection<GameBlock> gameBlocks, float alpha);
}
