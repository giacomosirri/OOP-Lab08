package it.unibo.oop.lab.advanced;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.file.Files;

public final class DrawNumberFileView implements DrawNumberView {

    private static final String DEFAULT_FILE = System.getProperty("user.dir") + "output.txt";
    private static final String NEW_GAME = ": a new game starts!";

    private PrintStream outputStream;
    private DrawNumberViewObserver observer;

    public DrawNumberFileView() {
        try {
            this.outputStream = new PrintStream(DEFAULT_FILE);
        } catch (FileNotFoundException e) {
            this.outputStream = null;
        }
    }

    public DrawNumberFileView(final String pathName) {
        try {
            this.outputStream = new PrintStream(pathName);
        } catch (FileNotFoundException e) {
            this.outputStream = null;
        }
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    public void setFile(final File file) throws FileNotFoundException {
        this.outputStream = new PrintStream(file);
        this.start();
    }

    public void setFile(final String pathName) throws FileNotFoundException {
        this.outputStream = new PrintStream(pathName);
        this.start();
    }

    @Override
    public void start() {
    }

    @Override
    public void numberIncorrect() {
        this.outputStream.print("\nIncorrect Number.. try again");
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
        this.outputStream.print("\nYou lost" + NEW_GAME);
    }

    private void printResult(final String message) {
        this.outputStream.print("\n" + message);
    }

    @Override
    public void displayError(final String message) {
        this.outputStream.print("\n" + message);
    }

}
