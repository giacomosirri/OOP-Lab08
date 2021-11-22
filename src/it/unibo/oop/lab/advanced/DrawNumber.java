package it.unibo.oop.lab.advanced;

/**
 * This class models a draw number game.
 *
 */
public interface DrawNumber {

    /**
     * Resets the game.
     */
    void reset();

    /**
     * Guesses a number.
     * 
     * @param n
     *            the guess
     * @return the result of the guess
     * @throws AttemptsLimitReachedException
     *             in case the game is lost
     */
    DrawResult attempt(int n) throws AttemptsLimitReachedException;
}
