/**
 * A subclass of {@code Direction}, representing the movement towards the upper right of the board. The move updates the coordinates of x by -1
 * & y by 1 (i.e., x-- & y++).
 */
public class UpRight extends Direction {

    /**
     * Sole constructor. It only passes the name of this move to the abstract class' constructor.
     */
    public UpRight() {
        super("Up-right");
    }

    /**
     * {@inheritDoc}
     * @implNote : The update coordinates for this move is {@code [-1, 1]}, representing {@code x--} & {@code y++} direction.
     */
    @Override
    public int[] getUpdateCoordinates() {
        return new int[]{-1, 1};
    }
}
