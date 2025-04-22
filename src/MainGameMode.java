/*
 * NAME:            Duc Cam Thai
 * STUDENT NUMBER:  7851908
 * COURSE:          COMP 2150   
 * INSTRUCTOR:      Heather Matheson
 * ASSIGNMENT:      3
 * QUESTION:        1
 * 
 * REMARKS: This program uses a series of interfaces and hierarchy of classes to implement a Greed game. It contains the following interfaces:
 *      - Viewable: meant to be printed onto the console.
 *      - GameLogical: validates the next state of the game and also resets the game's state. Extends Viewable.
 *      - Selectable: indicates that the item can be 'selected' during gameplay or in Menu.
 *      - Menuable: base interface for Menu, has setMessage method. Extends GameLogical.
 *      - GameBoard: interface for board representation on the console. Also extends Viewable.
 *      - Player: interface for any user or computer playing this game.
 * All classes created to run the Greed game not only built using hierarchy but also implements these interfaces, ensuring the game works properly
 * and does not violate any contracts declared by given interfaces.
 */

import java.io.IOException;

/**
 * Main driver class for our Greed Game. It reads an tutorial file from a given input pathname (which comes from the command line
 * arguments). It also contains the dimension for our Greed game. Once the game is created, it is then executed and keeps on running,
 * as long as the player prompts the program to stay in place
 * Most exceptions (such as {@link IOException} & {@link NumberFormatException}), whether from I/O errors or invalid arguments, are handled 
 * internally so that the program can terminate normally. The flow of the game is also handled internally so that 
 * accidental exceptions like {@link java.util.InputMismatchException} do not break the game.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class MainGameMode {
    

    /**
     * Main method to execute our program. Here we use the {@code args} array to input our number of rows, columns and file name 
     * along with the program execution command. The created game is then run with the player prompting required inputs to keep the game 
     * running.
     * @param args : The input arguments to be run along with this program.
     * @throws IllegalArgumentException If the there are less than 3 arguments passed into the program.
     */
    public static void main(final String... args) {

        if (args.length < 3) {

            //Throws this exception if there are less than 3 arguments
            throw new IllegalArgumentException("Please specify your board size and tutorial file: java GameMode [row] [column] [tutorial].txt");
        
        }
        try {

            int rows = Integer.parseInt(args[0]); //Number of rows
            int cols = Integer.parseInt(args[1]); //Number of columns
            String fileName = args[2]; //Gets the fileName

            //Creates the instance of the game and starts exceuting:
            gameFactory(rows, cols, fileName).run();

            //Prints the terminate message:
            System.out.println("Program terminates normally");

        } catch (NumberFormatException nfe) {

            //If the NumberFormatException is caught, prints the error message and terminates the program:
            System.err.println("Please enter your board dimensions in a number parsable format: java GameMode [row] [column]");
            System.exit(1);

        } catch (IOException ioe) {

            //If the IOException is caught, prints the error message and terminates the program:
            System.err.println("Something went wrong when load tutorial file. Please try again");
            System.exit(1);

        }
    }

    /**
     * Factory method that instantiates a new Greed game based on the given arguments:
     * <ul>
     *  <li> Number of rows.
     *  <li> Number of columns.
     *  <li> The name of the file to be loaded.
     * </ul>
     * @param rows : Number of rows.
     * @param cols : Number of columns.
     * @param fileName : The name of the file to be loaded.
     * @return A new instance of Game based on the given arguments.
     * @throws IOException If any I/O error occurs in reading the file.
     */
    public static Game gameFactory(int rows, int cols, String fileName) throws IOException {

        //Creates the player with the given coordinates and symbol:
        Player player = new HumanPlayer(rows/2, cols/2, '@');

        //Creates the game board based on the dimension and the player instance:
        GameBoard gameBoard = new GreedGameBoard(rows, cols, player);

        //Creates a new Menu:
        Menu menu = new Menu(player);

        //Creates the game logic:
        GameLogic gameLogic = new GameLogic(player);

        //Returns the new instance of this game:
        return new GreedGame(gameLogic, gameBoard, menu, fileName, player);
        
    }
}
