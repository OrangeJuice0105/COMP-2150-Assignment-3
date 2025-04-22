
/**
 * Abstract class that represents the direction that each move can take. It is created solely for the {@code Move} class.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public abstract class Direction {
    
    /**
     * The name representing the move's direction.
     */
    protected String moveName;

    /**
     * Protected constructor for subclasses of direction to implement. 
     * @param directionName : The direction name.
     */
    protected Direction(String directionName) {
        moveName = directionName;
    }

    /**
     * Returns the direction's name.
     * @return direction's name
     */
    public String getName() {
        return moveName;
    }
    /**
     * Returns a set of update rate coordinates based on the direction taken. The returned array has only 2 elements in it.
     * @return A set of coordinates used to move in the matrix. 
     * @implNote The first index represents the x-rate change while the second index represents the y-rate change. Each direction
     * must represent what the rate of change is for x and y.
     */
    public abstract int[] getUpdateCoordinates();

    /**
     * Returns the direction name in a String representation.
     * @return a String represents the direction's name.
     */
    @Override
    public String toString() {
        return getName();
    }
    
}
