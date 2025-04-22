import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a human player for the greed game. 
 * Everytime a player makes a choice, most of their choices are validated via {@code System.in}.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class HumanPlayer implements Player {
    
    /**
     * Current x-coordinate of the player
     */
    private int xCoor;

    /**
     * Current y-coordinate of the player
     */
    private int yCoor;

    /**
     * The {@code Scanner} used for player's input
     */
    private Scanner scanner;

    /**
     * The symbol associated with this player
     */
    private char symbol;

    /**
     * Creates a new instance of HumanPlayer associated with the coordinates and the given symbol.
     * @param x : The x-coordinate to be set to this player.
     * @param y : The y-coordinate to be set to this player
     * @param symbol : The character symbol associated with this player.
     * @throws IllegalArgumentException If the symbol is numeric, as numeric values are already reserved for this game.
     */
    public HumanPlayer(int x, int y, char symbol) {
        if (Character.isDigit(symbol)) {
            throw new IllegalArgumentException("Player symbol must not be a numeric character");
        }
        this.symbol = symbol;
        setCoordinates(x, y);
        scanner = new Scanner(System.in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCoordinates(int x, int y) {
        xCoor = x;
        yCoor = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCoordinates(int xRate, int yRate) {
        xCoor += xRate;
        yCoor += yRate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getXCoordinate() {
        return xCoor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getYCoordinate() {
        return yCoor;
    }

    /**
     * {@inheritDoc}
     * @throws InputMismatchException : If the choice prompted by the player does not match the expected type.
     */
    @Override
    public int nextChoice() {
        return scanner.nextInt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String nextAnswer() {
        return scanner.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String skip() {
        return scanner.nextLine();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char getSymbol() {
        return this.symbol;
    }
}
