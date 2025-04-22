import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a tutorial screen for beginners into this game. This screen appears before the player enters the game. It is ignored if the player
 * chooses not to view. It is one of the classes alongside {@link Instructions} to implement both {@code Viewable} & {@code Selectable} 
 * interfaces.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Tutorial implements Viewable, Selectable {

    /**
     * Simulates the player's choice
     */
    private Player userChoice;

    /**
     * String storing the tutorial page
     */
    private String tutorialPage;

    /**
     * Creates a {@code Tutorial} object that holds the contents of the tutorial file and the player's input
     * @param inputFileName : The input path to be read
     * @param player : The player to be associated with choice input.
     * @throws IOException If any I/O error occurs during reading.
     */
    public Tutorial(String inputFileName, Player player) throws IOException {
        userChoice = player;
        tutorialPage = loadTutorial(inputFileName);
    }   

    /**
     * Loads the tutorial from the given path name, then passed it into the tutorial page.
     * @param inputFileName : The abstract path name of the file to be read.
     * @return The full contents of the input file, stored into a String.
     * @throws IOException If any I/O error occurs during reading.
     * 
     * @implNote This method uses the {@link Files#readAllLines(Path)} method to read everything at once as opposed
     * to using a {@link BufferedReader} or a {@link Scanner} to read each line from the given file. The list's contents
     * are then stored into a {@code StringBuilder} by using the {@link List#forEach(java.util.function.Consumer)} to apply 
     * to every String in this list, with the {@code Consumer} being appending each line into the builder. The last newline character is
     * removed and the String from this builder is then returned.
     */
    private String loadTutorial(String inputFileName) throws IOException {

        //Extracts all lines from the given file using Files.readAllLines(Path) (with the path retrieved from Paths.get):
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        //Creates a new StringBuilder to create String:
        StringBuilder builder = new StringBuilder();

        //Calling forEach method to append the line into the builder (this is the same as invoking a for-each loop):
        lines.forEach(line -> builder.append(line).append("\n"));

        //Deletes the last newline character then returns the String built from the given file.
        return builder.deleteCharAt(builder.lastIndexOf("\n")).toString();

    }

    /**
     * Validates if the user's choice is "Y" or not (case ignored). (The original implementation will view 
     * the instruction if this method returns {@code true}).
     * @param v : A {@code Viewable} to be updated.
     * @param gl : A {@code GameLogical} associated with the game.
     * @return A boolean flag indicating if this instruction can be viewed to the user or not.
     */
    @Override
    public boolean select(Viewable v, GameLogical gl) {
        return userChoice.nextAnswer().equalsIgnoreCase("Y");
    }

    /**
     * Transfer the user to Game mode after viewing the instructions.
     */
    public void moveToGame() {
        userChoice.pressAny();
    }

    /**
     * Returns the contents of the instruction page as a String representation of this {@code Tutorial}.
     * @return the contents of the instruction page.
     */
    @Override
    public String toString() {
        return tutorialPage;
    }

    /**
     * Prints the tutorial page onto the console.
     */
    @Override
    public void view() {
        System.out.println(this);
    }
    
}
