package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * 
 * A very simple program using a graphical interface.
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("My second Java graphical interface");

    /**
     * Builds a new {@link SimpleGUI}.
     */
    public SimpleGUIWithFileChooser() {
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
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JTextField text = new JTextField();
        mainPanel.add(text, BorderLayout.CENTER);
        final JButton saveButton = new JButton("Save");
        mainPanel.add(saveButton, BorderLayout.SOUTH);
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
        /*
         * Add a new panel with a button and an unmodifiable text field.
         */
        final JPanel browsePanel = new JPanel(new BorderLayout());
        final JTextField currentFile = new JTextField(String.valueOf(ctrl.getFile()));
        currentFile.setEditable(false);
        final JButton browseButton = new JButton("Browse...");
        browsePanel.add(currentFile, BorderLayout.CENTER);
        browsePanel.add(browseButton, BorderLayout.LINE_END);
        /*
         * When pressing the "Browse..." button, open a file chooser and select a file.
         * If the operation is correctly processed, then set the chosen file as the current. 
         */
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                currentFile.setText(String.valueOf(showSaveDialog(ctrl)));
            }
        });
        mainPanel.add(browsePanel, BorderLayout.NORTH);
        this.frame.setContentPane(mainPanel);
    }

    private File showSaveDialog(final Controller ctrl) {
        final JFileChooser fileChooser = new JFileChooser(ctrl.getFile());
        final int result = fileChooser.showOpenDialog(this.frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            final File chosenFile = fileChooser.getSelectedFile();
            ctrl.setFile(chosenFile);
            return chosenFile;
        } else if (result != JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(frame, "An error has occurred", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Allows the interface to be seen on screen.
     */
    public void display() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] s) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser();
        gui.display();
    }
}