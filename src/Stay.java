/**
 * A subclass of {@code Direction}, representing the same position in the board. The move, as the name implies, does not update any coordinates.
 *
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Stay extends Direction {

    /**
     * Sole constructor. It only passes the name of this move to the abstract class' constructor.
     */
    public Stay() {
        super("Stay");
    }

    /**
     * {@inheritDoc}
     * @implNote : The update coordinates for this move is {@code [0, 0]}, indicating there is no change at all.
     */
    @Override
    public int[] getUpdateCoordinates() {
        return new int[]{0, 0};
    }
    
}
