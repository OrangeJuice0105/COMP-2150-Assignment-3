/**
 * Base interface to implement our game logic and menu screens.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public interface GameLogical extends Viewable {

    /**
     * Returns a boolean flag indicating if there is a next state from the given {@code Viewable} object.
     * @param v : A {@code Viewable} object to be used.
     * @return {@code false} if there is no next state.
     */
    boolean nextState(Viewable v);
    
    /**
     * Resets the state of this {@code GameLogical}.
     */
    void reset();
}
