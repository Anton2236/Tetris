package block;

import java.awt.*;

/**
 * Created by anton.
 */
public enum BlockColor {
    BLUE(Color.BLUE),
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    VIOLET(Color.MAGENTA),
    PINK(Color.PINK),
    ORANGE(Color.ORANGE);
    private final Color color;

    BlockColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }}
