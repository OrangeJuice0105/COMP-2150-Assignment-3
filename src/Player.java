import java.io.IOException;

/**
 * A playable interface for players in this game. A player can do the following things:
 * <ul>
 *  <li> Prompts choices into the game system.
 *  <li> Has status viewed on the game.
 *  <li> Keeps track of coordinates in the game.
 *  <li> Designs with a given symbol.
 * </ul>
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public interface Player {

    /**
     * Temporary storage holder for the skip bytes from {@code System.in}
     */
    static final int BLOCKS = 10;

    /**
     * Returns the player's next choice (usually in form of an Integer).
     * @return the player's next choice.
     */
    int nextChoice();

    /**
     * Returns the player's next choice (usually in form of a String).
     * @return the player's next answer.
     */
    String nextAnswer();

    /**
     * Lets the player skips the entire turn if there is something goes wrong.
     * @return the String of characters skipped.
     */
    String skip();

    /**
     * Returns the current x-coordinate of this player.
     * @return The x-coordinate at the current spot
     */
    int getXCoordinate();

    /**
     * Returns the current y-coordinate of this player.
     * @return The y-coordinate at the current spot
     */
    int getYCoordinate();

    /**
     * Updates the coordinate of the player (i.e., move the player) with the given rate
     * @param x : rate of change of x
     * @param y : rate of change of y
     */
    void updateCoordinates(int x, int y);

    /**
     * Sets the coordinate for this player.
     * @param newX : the new x-coordinate
     * @param newY : the new y-coordinate
     */
    void setCoordinates(int newX, int newY);

    /**
     * Returns the character symbol associated with this player.
     * @return a symbol for this player
     */
    char getSymbol();

    /**
     * Simulates the situation where the player presses any key to skip.
     */
    default void pressAny() {

        byte[] toSkip = new byte[BLOCKS];

        //Skips the input and handles the IOException if it occurs:
        try {
            System.in.read(toSkip);
        } catch (IOException ioe) {
            System.err.printf("An exception has occurred: %s\n", ioe);
        }
    }
    
    
}
