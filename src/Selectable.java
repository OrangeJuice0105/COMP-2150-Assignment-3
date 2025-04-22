/**
 * Indicating that the object implementing this interface can be "selected" via any inputs.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public interface Selectable {

    /**
     * Receives a {@code Viewable} object, updates its state then returns a flag indicating if is in game mode or not.
     * @param v : A {@code Viewable} to be updated.
     * @param gl : A {@code GameLogical} associated with the game
     * @return A boolean flag indicating if everything is still in game mode or not.
     */
    boolean select(Viewable v, GameLogical gl);//updates v, returns bool game mode
    
}
