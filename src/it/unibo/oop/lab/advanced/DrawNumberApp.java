package it.unibo.oop.lab.advanced;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final String DEFAULT_FILE = "view.txt";
    private DrawNumber model;
    private final Set<DrawNumberView> views;

    /**
     * 
     */
    public DrawNumberApp(final String configFile, final DrawNumberView...views) {
        this.views = new HashSet<>(List.of(views));
        for (final DrawNumberView thisView : this.views) {
            thisView.setObserver(this);
            thisView.start();
        }
        final Configuration config = new Configuration();
        try {
            final var configuration = config.getConstants(configFile);
            this.model = new DrawNumberImpl(configuration.get("minimum"), 
                    configuration.get("maximum"), configuration.get("attempts"));
        } catch (IOException e) {
            for (final DrawNumberView thisView : this.views) {
                thisView.displayError("Cannot load or read configuration file");
            }
            this.model = new DrawNumberImpl(0, 0, 0);
        }
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
        new DrawNumberApp("config.yml", 
                new DrawNumberViewImpl(), 
                new DrawNumberViewImpl(), 
                new DrawNumberFileView(System.getProperty("user.dir") + DEFAULT_FILE), 
                new StandardOutputView());
    }

}
