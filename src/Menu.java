import java.util.InputMismatchException;
import java.util.List;

/**
 * Menu screen for our Greed Game. This class implements the {@code Menuable} interface, along with methods from {@code GameLogical} to 
 * input to the User, prompting them to play the game or not by entering their option via {@code System.in}.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Menu implements Menuable {

    /**
     * The player associating with choice making before the game starts.
     */
    private Player userInput;

    /**
     * The menu items displayed on the screen for the player to choose.
     */
    private List<MenuItem> menuItems;

    /**
     * The message to be prompted onto the screen.
     */
    private String message;

    /**
     * Instantiates the menu with the player to make the choice. It also sets the message upon entering the program.
     * @param player : the player to make every choice.
     */
    public Menu(Player player) {
        userInput = player;
        menuItems = List.of(new EnterOption(), new ExitOption());
        setMessage("Welcome to ");
    }

    /**
     * Returns the message associating with this menu.
     * @return The message attached to this menu.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns a boolean flag indicating if there is we can enter game mode via the {@code Viewable} object.
     * @param v : A {@code Viewable} object to be checked.
     * @return {@code true} if the user accepts to enter the game.
     */
    @Override
    public boolean nextState(Viewable v) {

        boolean valid = false; //Flag indicating if the user has the correct input
        boolean gameMode = false; //Flag indicating if the user really enters the game

        //While loop to run until the user chooses the correct input.
        while (!valid) {
            try {

                //Gets user's next choice:
                int choice = userInput.nextChoice();

                if (choice > 0 && choice <= menuItems.size()) {

                    //The user makes a valid choice 
                    MenuItem item = menuItems.get(choice - 1);

                    //Gets the item that the user chooses, then switches valid flag to true to terminate loop:
                    gameMode = item.select(v, this);
                    valid = true;


                } else {

                    //Prompts the user to enter the correct number of choice, and skips all incorrect inputs:
                    System.out.print("Please enter the number of your choice: ");
                    userInput.skip();

                }
            } catch (InputMismatchException ime) {

                //Prompts the user to enter the correct number, and skips all incorrect inputs:
                System.err.print("Please enter the number of your choice: ");
                userInput.skip();

            }

        }    

        //Returns the flag:
        return gameMode;
        
    }

    /**
     * Resets the menu screen with the message.
     */
    @Override
    public void reset() {
        System.out.println(message);
    }

    /**
     * Echoes this menu screen onto the console.
     */
    @Override
    public void view() {
        System.out.println(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Returns a String representation of this {@code Menu}. The {@code Menu} is formatted with the {@code MenuItems} and the given options
     * for the {@code Player} to choose.
     * @return the String representation of this {@code Menu}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int ordinal = 0;
        for (MenuItem item : menuItems) {
            builder.append(++ordinal).append(". ").append(item).append("\n");
        }
        return builder.deleteCharAt(builder.lastIndexOf("\n")).toString();
    }

}