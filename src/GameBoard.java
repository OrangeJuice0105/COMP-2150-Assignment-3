import java.util.ArrayList;
import java.util.List;

/**
 * An interface representing a game board. Every game that has board layout implementing this interface must abide to the contract this
 * interface carries out. 
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public interface GameBoard extends Viewable {
    
    /**
     * Returns {@code true} if this board is still solvable at the time of this method call.
     * @return {@code true} if the board can still be solved.
     */
    boolean isSolvable();

    /**
     * Updates the board with the player moved to a specific location using said move.
     * @param move : The movement in the board to be updated.
     * @param rates : The coordinates rate to be updated.
     * @return The total number of points accumulated after moving.
     */
    int updateBoard(Move move, int[] rates);

    /**
     * Resets the board to the initial state.
     */
    void reset();
    
    /**
     * Retrieves the given {@code Move}, then verifies if its direction of movement can be performed in this board or not.
     * @param move : The {@code Move} to be performed in this board.
     * @return {@code true} if the move can be perfomed.
     */
    boolean validMove(Move move);

    /**
     * Represents the list of all possible directions that the player can take. Uses {@link java.util.List#of(Direction...)}.
     * The order is the same as the user's prompt.
     */
    static List<Direction> possibleDirections = List.of(new DownLeft(), new Down(), new DownRight(), 
                                                        new Left(), new Stay(), new Right(),
                                                        new UpLeft(), new Up(), new UpRight());

    /**
     * Returns the current dimension of this board. The first index is the number of rows and the second index is the number of columns.
     * @return an {@code int[]} array representing the current dimension of this board.
     */                                            
    int[] getDimensions();
    
    /**
     * Returns the list of every possible moves that the player can take to solve this board.
     * @return List of every possible moves that the player can take to solve this board.
     */
    public static List<Move> getPossibleMoves() {
        List<Move> possibleMoves = new ArrayList<>();
        for (Direction dir : possibleDirections) {
            possibleMoves.add(new Move(dir));
        }
        return possibleMoves;
    }

}
