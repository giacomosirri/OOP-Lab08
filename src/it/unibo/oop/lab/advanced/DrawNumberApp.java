package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int NUMBER_OF_CONSTANTS = 3;
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * 
     */
    public DrawNumberApp() {
        this.model = new DrawNumberImpl(initializeConstants()[0], initializeConstants()[1], initializeConstants()[2]);
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    private int[] initializeConstants() {
        try (BufferedReader bf = new BufferedReader(new FileReader("config.yml"))) {
            int[] allConstants = new int[NUMBER_OF_CONSTANTS];
            for (int i = 0; i < NUMBER_OF_CONSTANTS; i++) {
                final String thisLine = bf.readLine();
                if (thisLine != null) {
                    allConstants[i] = Integer.parseInt(thisLine.split(":")[1]);
                }
            }
            return allConstants;
        } catch (FileNotFoundException e) {
            view.displayError("Cannot load configuration file");
        } catch (NumberFormatException | IOException e) {
            view.displayError("An error has occurred while reading the configuration file");
        }
        return new int[0];
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
