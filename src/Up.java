/**
 * A subclass of {@code Direction}, representing the vertical up move in the board. The move updates the coordinates of x by -1 (i.e., x--).
 */
public class Up extends Direction {

    /**
     * Sole constructor. It only passes the name of this move to the abstract class' constructor.
     */
    public Up() {
        super("Up");
    }

    /**
     * {@inheritDoc}
     * @implNote : The update coordinates for this move is {@code [-1, 0]}, representing {@code x--} direction.
     */
    @Override
    public int[] getUpdateCoordinates() {
        return new int[]{-1, 0};
    }
    
}
