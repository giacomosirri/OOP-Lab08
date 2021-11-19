package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleController implements Controller {

    private final List<String> history;

    public SimpleController() {
        this.history = new ArrayList<>();
    }

    @Override
    public void setTextToPrint(final String text) {
        this.history.add(this.history.size(), Objects.requireNonNull(text, "Text cannot be empty"));
    }

    @Override
    public String getTextToPrint() {
        return this.history.get(this.history.size() - 1);
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<String>(this.history);
    }

    @Override
    public void printString() {
        try {
            final String nextToPrint = this.getTextToPrint();
            System.out.println(nextToPrint);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Text cannot be empty");
        }
    }
}
