
/**
 * A subclass of {@code Direction}, representing the movement towards the upper left of the board. The move updates the coordinates of x & y
 * by -1 (i.e., x-- & y--).
 */
public class UpLeft extends Direction {

    /**
     * Sole constructor. It only passes the name of this move to the abstract class' constructor.
     */
    public UpLeft() {
        super("Up-left");
    }

    /**
     * {@inheritDoc}
     * @implNote : The update coordinates for this move is {@code [-1, -1]}, representing {@code x--} & {@code y--} direction.
     */
    @Override
    public int[] getUpdateCoordinates() {
        return new int[]{-1, -1};
    }

}
