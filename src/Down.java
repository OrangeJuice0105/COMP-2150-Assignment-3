/**
 * A subclass of {@code Direction}, representing the vertical down move in the board. The move updates the coordinates of x by 1 (i.e., x++).
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Down extends Direction {

    /**
     * Sole constructor. It only passes the name of this move to the abstract class' constructor.
     */
    public Down() {
        super("Down");
    }

    /**
     * {@inheritDoc}
     * @implNote : The update coordinates for this move is {@code [1, 0]}, representing {@code x++} direction.
     */
    @Override
    public int[] getUpdateCoordinates() {
        return new int[]{1, 0};
    }
    
}
