/**
 * Base interface for {@code Menu} class. It inherits the functionality of {@code GameLogical} interface 
 * (including {@link GameLogical#nextState(Viewable)} & {@link GameLogical#view()})
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public interface Menuable extends GameLogical {

    /**
     * Sets the given message to print onto the console.
     * @param message : The message to printed onto the console.
     */
    void setMessage(String message);
}
