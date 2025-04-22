/**
 * Represents the item to exit the game.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class ExitOption extends MenuItem {

    /**
     * Sole constructor. It only passes the option {@code "Quit Program?"} to the abstract class' constructor.
     */
    public ExitOption() {
        super("Quit Program?");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.option;
    }

    /**
     * Returns {@code false} upon being selected, indicating that the user really exits the game.
     * @return {@code false} by default.
     */
    @Override
    public boolean select(Viewable v, GameLogical gl) {
        return false;
    }    
}