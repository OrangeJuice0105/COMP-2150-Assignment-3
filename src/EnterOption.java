/**
 * Represents the item to enter the game.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class EnterOption extends MenuItem {

    /**
     * Sole constructor. It only passes the option {@code "Play Game?"} to the abstract class' constructor.
     */
    public EnterOption() {
        super("Play Game?");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.option;
    }
    
    /**
     * Returns {@code true} upon being selected, indicating that the user really enters the game.
     * @return {@code true} by default.
     */
    @Override
    public boolean select(Viewable v, GameLogical gl) {
        return true;
    }
}
