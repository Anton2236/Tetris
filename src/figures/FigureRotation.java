package figures;

/**
 * Created by anton.
 */
public enum FigureRotation {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static FigureRotation getNext(FigureRotation rotation) {
        FigureRotation nextRotation;
        switch (rotation) {

            case UP:
                nextRotation = RIGHT;
                break;
            case RIGHT:
                nextRotation = DOWN;
                break;
            case DOWN:
                nextRotation = LEFT;
                break;
            case LEFT:
            default:
                nextRotation = UP;
                break;
        }
        return nextRotation;
    }}
