package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * 
 * A very simple program using a graphical interface.
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JTextField text = new JTextField();
        mainPanel.add(text, BorderLayout.NORTH);
        final JTextArea printHistory = new JTextArea();
        mainPanel.add(printHistory, BorderLayout.CENTER);
        final JButton printButton = new JButton("Print");
        mainPanel.add(printButton, BorderLayout.PAGE_END);
        final JButton showHistory = new JButton("Show history");
        mainPanel.add(showHistory, BorderLayout.WEST);
        final Controller ctrl = new SimpleController();
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                ctrl.setTextToPrint(text.getText());
                ctrl.printString();
            }
        });
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> history = ctrl.getHistory();
                printHistory.setText("");
                for (final String thisString : history) {
                    printHistory.append(thisString + "\n");
                }
            }
        });
        this.frame.setContentPane(mainPanel);
    }

    /**
     * Allows the interface to be seen on screen.
     */
    public void display() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] s) {
        final SimpleGUI gui = new SimpleGUI();
        gui.display();
    }
}
