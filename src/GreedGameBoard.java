import java.util.Random;

/**
 * A board for Greed (a number eating game). It implements the interface {@code GameBoard} 
 * and abides to the contract declared by {@code GameBoard}.
 */
public class GreedGameBoard implements GameBoard {

    /**
     * Represents '1' in ASCII form.
     */
    private static final int ASCII_ONE = (int) '1';

    /**
     * Represents '9' in ASCII form.
     */
    private static final int ASCII_NINE = (int) '9';

    /**
     * The board associated with this game.
     */
    private char[][] board;

    /**
     * The number of rows for this board.
     */
    private int row;

    /**
     * The number of columns for this board.
     */
    private int column;

    /**
     * The associated player for this game.
     */
    private Player player;

    /**
     * Instantiates a new GameBoard with the given dimensions and the Player to play this game.
     * @param rows : The number of rows to be passed into this board.
     * @param columns : The number of columns to be passed into this board.
     * @param player : The {@code Player} playing this game.
     */
    public GreedGameBoard(int rows, int columns, Player player) {

        //Instantiates the dimension then allocates a new board:
        this.row = rows;
        this.column = columns;
        board = new char[rows][columns];

        //Assigns the player and starts loading the board:
        this.player = player;
        loadBoard(rows, columns);
    }

    /**
     * Helper method to initialize the board. Every indices is filled with random numbers from '1' to '9', and the centre is filled
     * with the player's symbol.
     * @param rows : The number of rows in this board.
     * @param columns : The number of columns in this board.
     */
    private void loadBoard(int rows, int columns) {

        //Random number generator to fill the board:
        Random randomGenerator = new Random();

        //For loop to fill the entire board with ASCIIs from 1 to 9 randomly at all indices:
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                board[r][c] = (char) randomGenerator.nextInt(ASCII_ONE, ASCII_NINE + 1);
            }
        }

        //Assigns the player's position to the middle:
        board[rows/2][columns/2] = player.getSymbol();
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateBoard(Move move, int[] rates) {

        //Gets the current player's coordinate:
        int currX = player.getXCoordinate();
        int currY = player.getYCoordinate();
        
        //Consumes the first space.
        board[currX][currY] = ' ';

        //Gets the update coordinates then updates the player to the given position:
        int updateX = rates[0];
        int updateY = rates[1];
        player.updateCoordinates(updateX, updateY);

        //Updates the current coordinate:
        currX += updateX;
        currY += updateY;

        //Gets the number of steps:
        int steps = Character.getNumericValue(board[currX][currY]);

        //Prompts the player that they have moved to where by how many steps first before moving to the number-eating algorithm:
        System.out.printf("You moved %s by %d steps\n", move, steps);
        return move(steps, updateX, updateY);

    }

    /**
     * Moves the current player to the specified position. The total number of steps taken is returned.
     * @param steps : The total number of steps that the player needs to move
     * @param updateX : The rate of change for x.
     * @param updateY : The rate of change for y.
     * @return the number of steps that the player moved in the board.
     */
    private int move(int steps, int updateX, int updateY) {

        //Gets the current coordinates:
        int currX = player.getXCoordinate();
        int currY = player.getYCoordinate();

        //For loop to update the board:
        for(int i = 0; i < steps - 1; i++) {

            //Removes the number at the current spot, then updates the coordinate each iteration:
            board[currX][currY] = ' '; 
            currX += updateX; 
            currY += updateY;

        }

        //Sets the player's coordinate at the last spot:
        board[currX][currY] = player.getSymbol();
        player.setCoordinates(currX, currY);

        //Returns the number of steps traversed in the board:
        return steps;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        player.setCoordinates(row/2, column/2);
        loadBoard(row, column);
    }

    @Override
    public void view() {
        System.out.println(this);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                builder.append(board[r][c]);
            }
            builder.append("\n");
        }
        return builder.deleteCharAt(builder.lastIndexOf("\n")).toString();
    }

    /**
     * Checks if the board is still solvable or not. It is considered solvable if there are still valid moves to be performed.
     * @return {@code true} if the board can still be solved.
     * @implNote This method uses the {@link java.util.stream.Stream} class to reduce overhead coding, saving resources.
     * The list of possible moves are first converted into a {@code Stream}, then is filtered with any move with that stays in the same spot.
     * The {@link java.util.stream.Stream#anyMatch(java.util.function.Predicate)} is then used to verify if any of the moves can be performed 
     * or not.
     */
    @Override
    public boolean isSolvable() {
        return GameBoard.getPossibleMoves(). //Gets all possible moves
                stream(). //Returns a new Stream based on this list
                filter(move -> !(move.getDirection() instanceof Stay)). //Filters move that stay in the same position.
                anyMatch(move -> validMove(move)); //Returns if any of the moves can be performed or not.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validMove(Move move) {
        char c;
        int currX = player.getXCoordinate();
        int currY = player.getYCoordinate();
        boolean valid = false;
        Direction direction = move.getDirection();
        int[] update = direction.getUpdateCoordinates();
        int updateX = update[0];
        int updateY = update[1];
        int newX = currX + updateX;
        int newY = currY + updateY;
        if (newX < 0 || newX >= row || newY < 0 || newY >= column) {
            valid = false;
        } else {
            c = board[newX][newY]; 
            if (c == ' ') {
                valid = false;
            } else {
                int steps = Character.getNumericValue(c); 
                valid = checkValidPath(steps, updateX, updateY, newX, newY);
            }
        }
        return valid;
    }

    private boolean checkValidPath(int steps, int updateX, int updateY, int startX, int startY) {
        for (int i = 0; i < steps - 1; i++) {
            startX += updateX; 
            startY += updateY;
            if (startX < 0 || startX >= row || startY < 0 || startY >= column) {
                return false;
            } 
            if (board[startX][startY] == ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getDimensions() {
        return new int[]{row, column};
    }
    
}
