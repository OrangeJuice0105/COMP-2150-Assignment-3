import java.io.IOException;

/**
 * Concrete Greed game implementation of the abstract {@code Game} class. The game consists of a game logic, a game board
 * associating with the game, the menu screen with the player interacting with this game.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class GreedGame extends Game {

    /**
     * The game logic associating with Greed
     */
    private GameLogic game; 

    /**
     * The game board for this given Game
     */
    private GameBoard board; 

    /**
     * The menu screen for this game
     */
    private Menu menu;

    /**
     * The boolean flag indicating if the user prompts to go in game mode or not
     */
    private boolean isGamemode;

    /**
     * The tutorial screen (available if the user wants to view it).
     */
    private Tutorial tutorialScreen;

    /**
     * Creates an insance of Greed game based on the given logic, game board, menu, the tutorial file and the player instance.
     * @param gl : The game logic for Greed
     * @param gb : The game board for Greed
     * @param m : The menu screen
     * @param fileName : The tutorial file to be loaded
     * @param player : The player to play this game
     * @throws IOException If any I/O error occurs during reading the tutorial file.
     */
    public GreedGame(GameLogic gl, GameBoard gb, Menu m, String fileName, Player player) throws IOException {

        //Inherits the super class' constructor:
        super(null, null, null);

        //Instantiates the fields:
        game = gl;
        board = gb;
        menu = m;

        //Loads the tutorial:
        tutorialScreen = new Tutorial(fileName, player);

    }
    
    /**
     * Runs the instance of our Greed game.
     * @implNote The game keeps on running until the user does not prompt to quit the game.
     */
    @Override
    public void run() {

        //Sets the message to the menu, then prints the message onto the console:
        menu.setMessage("Welcome to Greed");
        System.out.println(menu.getMessage());

        //Flag to indicate if the game gets terminated or not:
        boolean terminate = false;

        //While loop to run until the user chooses to terminate:
        while (!terminate) {

            //Views the option onto the menu:
            menu.view();

            //Retrieves player's choice 
            isGamemode = menu.nextState(menu);
            if (!isGamemode) {
                terminate = true;
            } else {
                System.out.print("Do you want to view the tutorial before starting? [y/N]: ");
                if (tutorialScreen.select(board, game)) {
                    tutorialScreen.view();
                    System.out.print("Press any key to start the game: ");
                    tutorialScreen.moveToGame();
                }
                while (isGamemode) {
                    game.view();
                    board.view();   
                    isGamemode = game.nextState(board);
                }
                menu.setMessage("Welcome back to Greed");
                menu.reset();
                board.reset();
                game.reset();
            }
        }
    }
}
