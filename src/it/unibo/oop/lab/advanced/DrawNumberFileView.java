package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DrawNumberFileView implements DrawNumberView {

    private static final String FILE_NAME = "";
    private static final String NEW_GAME = ": a new game starts!";

    private PrintStream outputFile;
    private DrawNumberViewObserver observer;

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void start() {
        try {
            this.outputFile = new PrintStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            this.displayError("File not found");
        }
    }

    @Override
    public void numberIncorrect() {
        this.outputFile.print("\nIncorrect Number.. try again");
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
        this.outputFile.print("\nYou lost" + NEW_GAME);
    }

    private void printResult(final String message) {
        this.outputFile.print("\n" + message);
    }

    @Override
    public void displayError(final String message) {
        this.outputFile.print("\n" + message);
    }

}
