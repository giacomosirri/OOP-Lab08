package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class SimpleController implements Controller {

    private final List<String> history;

    public SimpleController() {
        this.history = new ArrayList<>();
    }

    public SimpleController(final String text) {
        this.history = new ArrayList<>();
        this.history.add(text);
    }

    @Override
    public void setTextToPrint(final String text) {
        this.history.add(this.history.size(), text);
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
        System.out.println(this.getTextToPrint());
    }
}
