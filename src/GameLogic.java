import java.util.InputMismatchException;
import java.util.List;

/**
 * A simple game logic for the Greed game, implementing the {@link GameLogical} interface. It validates every player's input,
 * then updates the board each time a valid move is made. It is also responsible for quitting the game if the player
 * chooses to do so.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class GameLogic implements GameLogical {

    /**
     * The player for this game
     */
    private Player player;

    /**
     * The current score
     */
    private int score;

    /**
     * Indicating the lower bound to validate user's choice
     */
    private static final int FIRST_CHOICE = 1;

    /**
     * Indicating the upper bound to validate user's choice
     */
    private static final int LAST_CHOICE = 9;

    /**
     * Creates a GameLogic based on the input player.
     * @param player : The player playing this game
     */
    public GameLogic(Player player) {
        this.player = player;
        score = 0;
    }

    /**
     * Prints the player's score onto the console.
     */
    @Override
    public void view(){
        System.out.printf("Player score: %d\n", score);
    }
    
    /**
     * Resets the score back to 0.
     */
    @Override
    public void reset() {
        score = 0;
    }

    /**
     * Gets the total amount of points the player scored.
     * @return The total score.
     */
    public int getTotalScore() {
        return score;
    }
    
    /** 
     * Gets the user's next choice in playing the game. If the player enters 0, this method returns {@code false} directly and exits the game. 
     * Otherwise the board is updated after each move and the method returns {@code true}.
     * @param v : The {@code Viewable}, preferably a {@code GameBoard} to validate next state,.
     * @return a flag indicating if it is still in game mode or not. 
     * Also returns false directly if the {@code Viewable} is not a {@code GameBoard}.
     */
    @Override
    public boolean nextState(Viewable v){
        
        if (v instanceof GameBoard gameBoard) {

            //Input argument is a GameBoard

            boolean validChoice = false; //Marks if the user made a valid choice or not
            boolean solvable = true; //Marks if the board is still solvable or not
            boolean inGame = true; //Boolean flag indicating if there is a next state or not.

            //Prompts the user to enter the move or view instructions:
            System.out.print("Enter move option (0 to give up, or 10 to view instructions): ");

            int choice; //Choice that the user made
            
            List<Move> moves = GameBoard.getPossibleMoves(); //Gets the list of possible moves
            Instructions instructions = new Instructions(moves); //Loads the instructions with the moves list

            //While loop to run until the user made a valid choice or the board is unsolvable:
            while (!validChoice && solvable) {
                if (!gameBoard.isSolvable()) {

                    //If the board is unsolvable, ends the game and prompts the player to press 0 to exit the game:
                    System.out.append("\n").print("No more moves. Press 0 to give up: ");

                    try {
                        if (player.nextChoice() == 0) {

                            //If the player presses 0, then exits the game
                            inGame = false;
                            solvable = false;
    
                        } else {
    
                            //If the user prompts any number other than 0, makes them type their choice again:
                            System.err.println("Please press 0 to give up!");
                            player.skip();

                        }
                    } catch (NumberFormatException nfe) {

                        //If the user prompts anything other than a number, makes them type their choice again:
                        System.err.println("Please press 0 to give up!");
                        player.skip();

                    }

                } else {
                    try {

                        //Gets the user's next choice:
                        choice = player.nextChoice();


                        if ((choice >= FIRST_CHOICE && choice <= LAST_CHOICE)) {

                            //If user gets the valid choice, retrieves the move from the list and gets the coordinates:
                            Move move = moves.get(choice - 1);
                            int[] update = move.getDirection().getUpdateCoordinates();

                            
                            if (move.select(gameBoard, this)) {

                                //If the move can be selected (i.e., can be performed in the board), performs said move and updates the board:
                                if (!(move.getDirection() instanceof Stay)) {

                                    //Updates score if the direction is not a Stay instance:
                                    score += gameBoard.updateBoard(move, update);

                                } else {

                                    //Prints the message informing the player that they chose to stay at the same place:
                                    System.out.println("You chose to stay at the same spot");

                                }

                                //Switches the boolean flag to true:
                                validChoice = true;   

                            } else {

                                //Prompts the user to try again if such move cannot be performed:
                                System.out.print("That is an invalid move. Try again: ");

                            }

                        } else if (choice == 0) {

                            //If the user chooses to give up, switches the inGame flag to false and terminates the loop:
                            System.out.println("You chose to give up. Play again next time!");
                            inGame = false;
                            validChoice = true;

                        } else if (choice == 10) {

                            //If the user prompts to view the instructions, select this option and displays them onto the console:
                            instructions.select(gameBoard, this);

                            //Prompts the player to enter their move option once they viewed the instructions:
                            System.out.print("Enter move option (0 to give up): ");

                        } else {

                            //Prompts the player to try again if they enter any choice beyond 0-9:
                            System.out.print("Move not found. Please try again: ");

                        }

                    } catch (InputMismatchException ime) {

                        //If the user prompts anything other than a number, makes them type their choice again:
                        System.err.print("Please enter the number of your choice: ");
                        player.skip();

                    }
                    
                }

            }

            //Returns the inGame flag indicating if there is a next state or not:
            return inGame;

        } else {

            //Returns false if v is not an instance of GameBoard:
            return false;

        }
    }

    /**
     * Validates of the given {@code Move} is invalid for the {@code Board} or not.
     * @param board : The board where the {@code Move} is validated.
     * @param move : The {@code Move} to be validated.
     * @return {@code true} if the {@code Move} can be performed in this board.
     */
    public boolean checkMove(GameBoard board, Move move) {
        return board.validMove(move);
    }
}
