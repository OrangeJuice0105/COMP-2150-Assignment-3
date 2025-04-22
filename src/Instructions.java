import java.util.List;

/**
 * This class lets the user knows which move to choose during the game. It is one of the classes alongside {@link ViewTutorial} to implement
 * both {@code Viewable} & {@code Selectable} interfaces.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Instructions implements Viewable, Selectable {

    /**
     * The list of moves to be printed onto the console.
     */
    private List<Move> moves;

    /**
     * Passes the list of {@code Moves} into this instruction class to be viewed in {@code System.out}.
     * @param possibleMoves : The list of all possible moves to be viewed.
     */
    public Instructions(List<Move> possibleMoves) {
        moves = possibleMoves;
    }

    /**
     * Echoes the given list of instructions onto the screen upon being selected, then returns {@code true} since we are still in game mode.
     * @return {@code true} by default.
     */
    @Override
    public boolean select(Viewable v, GameLogical gl) {
        view();
        return true;
    }

    /**
     * Prints this instruction onto the console.
     */
    @Override
    public void view() {
        System.out.println(this);
    }

    /**
     * Returns a {@code String} representation of this instruction. It starts out by prompting the list of all possible moves, then 
     * each moves are appended into the builder with the number to move in the game that the user can select.
     * @return the {@code String} representation of this instruction.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Here is the list of all possible moves: \n");
        int ordinal = 0;
        for (Move move : moves) {
            builder.append(++ordinal).append(". ").append(move).append("\n");
        }
        return builder.deleteCharAt(builder.lastIndexOf("\n")).toString();
    }
    
}
