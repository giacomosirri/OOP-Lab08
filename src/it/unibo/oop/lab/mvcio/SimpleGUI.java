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
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("My first Java graphical interface");

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) It has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextArea with a button "Save" right
     * below (see "ex02.png" for the expected result). SUGGESTION: Use a JPanel with
     * BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The program asks the controller to save the file if the button "Save" gets
     * pressed.
     * 
     * Use "ex02.png" (in the res directory) to verify the expected aspect.
     */

    /**
     * builds a new {@link SimpleGUI}.
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
         */
        this.frame.setLocationByPlatform(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JPanel pane = new JPanel(new BorderLayout());
        final JTextField text = new JTextField();
        pane.add(text, BorderLayout.CENTER);
        final JButton saveButton = new JButton("Save");
        pane.add(new JButton("Save"), BorderLayout.SOUTH);
        final Controller ctrl = new Controller();
        System.out.println(ctrl.getFile());
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
        this.frame.setContentPane(pane);
        this.frame.setLocationByPlatform(true);
    }

    public void display() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] s) {
        final SimpleGUI gui = new SimpleGUI();
        gui.display();
    }
}
