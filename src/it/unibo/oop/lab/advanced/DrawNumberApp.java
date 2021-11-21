package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int NUMBER_OF_CONSTANTS = 3;
    private final DrawNumber model;
    private final Set<DrawNumberView> views = new HashSet<>();

    /**
     * 
     */
    public DrawNumberApp() {
        this.model = new DrawNumberImpl(initializeConstants()[0], initializeConstants()[1], initializeConstants()[2]);
        this.views.add(new DrawNumberViewImpl());
        for (final DrawNumberView thisView : this.views) {
            thisView.setObserver(this);
            thisView.start();
        }
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
            for (final DrawNumberView thisView : this.views) {
                thisView.displayError("Cannot load configuration file");
            }
        } catch (NumberFormatException | IOException e) {
            for (final DrawNumberView thisView : this.views) {
                thisView.displayError("An error has occurred while reading the configuration file");
            }
        }
        return new int[0];
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView thisView : this.views) {
                thisView.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView thisView : this.views) {
                thisView.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView thisView : this.views) {
                thisView.limitsReached();
            }
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
