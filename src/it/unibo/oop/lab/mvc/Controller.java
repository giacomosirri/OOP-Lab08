package it.unibo.oop.lab.mvc;

import java.util.List;

/**
 * A controller that prints strings and has memory of the strings it printed.
 */
public interface Controller {

    /**
     * Sets the next string to print.
     *
     * @param text
     *          the text that must be printed next
     * @throws an IllegalArgumentException if the text is null
     */
    void setTextToPrint(String text);

    /**
     * Gets the next string to print.
     * 
     * @return the text that is the next one 
     */
    String getTextToPrint();

    /**
     * Gets the history of the strings already printed.
     * 
     * @return the history of the printed strings
     */
    List<String> getHistory();

    /**
     * Prints the current string. 
     * 
     * @throws an IllegalStateException if the current string is unset
     */
    void printString();

}
