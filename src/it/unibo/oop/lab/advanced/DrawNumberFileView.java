package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Implementation of {@link DrawNumberView} which writes the match log on file.
 *
 */
public final class DrawNumberFileView implements DrawNumberView {

    private static final String DEFAULT_FILE = System.getProperty("user.dir") + "output.txt";
    private static final String NEW_GAME = ": a new game starts!";

    private File outputFile; 
    private PrintStream outputStream;
    private DrawNumberViewObserver observer;

    /**
     * Starts the stream on the default file.
     */
    public DrawNumberFileView() {
        this.start();
    }

    /**
     * Starts the stream on the specified path name.
     * 
     * @param pathName
     *                  the path name of the file used as stream
     */
    public DrawNumberFileView(final String pathName) {
        this.outputFile = new File(pathName);
        this.start();
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    /**
     * Sets a file as the current stream.
     * 
     * @param file
     *          the file to be used as stream, in form of a {@link java.io.File}
     */
    public void setFile(final File file) {
        this.outputFile = file;
        this.start();
    }

    /**
     * Sets a file as the current stream.
     * 
     * @param pathName
     *          the file to be used as stream, in form of a {@link java.lang.String}
     */
    public void setFile(final String pathName) {
        this.setFile(new File(pathName));
    }

    @Override
    public void start() {
        try {
            this.outputStream = new PrintStream(this.outputFile);
        } catch (FileNotFoundException e) {
            try {
                this.outputStream = new PrintStream(DEFAULT_FILE);
            } catch (FileNotFoundException e1) {
                this.outputStream = null;
            }
        } 
    }

    private boolean isStreamSet() {
        return this.outputStream != null;
    }

    @Override
    public void numberIncorrect() {
        if (isStreamSet()) {
            this.outputStream.print("\nIncorrect Number.. try again");
        }
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            this.printResult(res.getDescription());
            return;
        case YOU_WON:
            this.printResult(res.getDescription());
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
        observer.resetGame();
    }

    @Override
    public void limitsReached() {
        if (isStreamSet()) {
            this.outputStream.print("\nYou lost" + NEW_GAME);
        }
    }

    private void printResult(final String message) {
        if (isStreamSet()) {
            this.outputStream.print("\n" + message);
        }
    }

    @Override
    public void displayError(final String message) {
        if (isStreamSet()) {
            this.outputStream.print("\n" + message);
        }
    }

}
