/**
 * A subclass of {@code Direction}, representing the movement towards the right side of the board. The move updates the coordinates of y by 1 (i.e., y++).
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Right extends Direction {

    /**
     * Sole constructor. It only passes the name of this move to the abstract class' constructor.
     */
    public Right() {
        super("Right");
    }
    
    /**
     * {@inheritDoc}
     * @implNote : The update coordinates for this move is {@code [0, 1]}, representing {@code y++} direction.
     */
    @Override
    public int[] getUpdateCoordinates() {
        return new int[]{0, 1};
    }
    
}

