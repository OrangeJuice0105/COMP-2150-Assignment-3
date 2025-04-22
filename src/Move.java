
/**
 * {@code Selectable} object that represents a user's choice of movement in the {@code GreedGame}. If selected, it will validate if the move
 * can be done or not.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Move implements Selectable {

    /**
     * The direction in the board that this move holds.
     */
    private Direction direction;

    /**
     * Creates a Move instance with the given Direction
     * @param direction : The direction in the board to be associated with this move.
     */
    public Move(Direction direction) {
        this.direction = direction;
    }

    /**
     * Validates the move once it selected. If the move can be performed after being selected, returns {@code true}.
     * Otherwise returns {@code false}.
     * @param v : A {@code Viewable} to be updated.
     * @param gl : A {@code GameLogical} associated with the game
     * @return A boolean flag indicating if the move can be selected to update the board or not.
     */
    @Override
    public boolean select(Viewable v, GameLogical gl) {
        return (!(v instanceof GameBoard board && gl instanceof GameLogic logic)) ? false : logic.checkMove(board, this);
    }

    /**
     * Returns the direction that this move holds
     * @return The direction information
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Returns a String representation of this move. It essentially returns the direction's name only.
     * @return The String representation of this move.
     */
    @Override
    public String toString() {
        return direction.toString();
    }

}
