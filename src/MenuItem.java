
/**
 * The {@code Selectable} item to be placed in the menu screen. It can be selected by the player upon entering the game.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public abstract class MenuItem implements Selectable {

    /**
     * The option that the user can choose.
     */
    protected String option;

    /**
     * Protected constructor for subclasses of MenuItem to implement. 
     * @param option : The option name.
     */
    public MenuItem(String option) {
        this.option = option;
    }

    /**
     * Returns the option choice
     * @return the option choice.
     */
    public String getOption() {
        return this.option;
    }

    /**
     * Returns the String representation of this option.
     * @return A string representation of this option.
     */
    public abstract String toString();
}
