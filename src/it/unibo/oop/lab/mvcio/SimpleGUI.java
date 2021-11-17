package it.unibo.oop.lab.mvcio;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * 
 * A very simple program using a graphical interface.
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first Java graphical interface");

    /**
     * Builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        this.frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         * This application must also be terminated when the interface is closed. 
         */
        this.frame.setLocationByPlatform(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /*
         * Add a panel with a JTextField and a JButton
         */
        final JPanel pane = new JPanel(new BorderLayout());
        final JTextField text = new JTextField();
        pane.add(text, BorderLayout.CENTER);
        final JButton saveButton = new JButton("Save");
        pane.add(saveButton, BorderLayout.SOUTH);
        this.frame.setContentPane(pane);
        /*
         * Initialize a controller and use it so that pressing the "Save" button
         * allows the content of the text area to be stored in the current file. 
         */
        final Controller ctrl = new Controller();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ctrl.writeOnFile(text.getText());
                } catch (IOException exc) {
                    JOptionPane.showMessageDialog(frame, exc.getMessage(), "Cannot save on this file", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
