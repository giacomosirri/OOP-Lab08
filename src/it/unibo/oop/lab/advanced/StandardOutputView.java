package it.unibo.oop.lab.advanced;

public final class StandardOutputView implements DrawNumberView {

    private DrawNumberViewObserver observer;
    private static final String NEW_GAME = ": a new game starts!";

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void start() {
    }

    @Override
    public void numberIncorrect() {
        System.out.println("\nIncorrect Number.. try again");
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            System.out.println(res.getDescription());
            return;
        case YOU_WON:
            System.out.println(res.getDescription());
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
        observer.resetGame();
    }

    @Override
    public void limitsReached() {
        System.out.println("\nYou lost" + NEW_GAME);
    }

    @Override
    public void displayError(final String message) {
        System.err.println(message);
    }

}
