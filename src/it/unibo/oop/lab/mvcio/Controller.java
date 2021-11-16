package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

/**
 * 
 */
public class Controller {

    static final String HOMEDIR = System.getProperty("user.dir");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "output.txt";
    private static final String FILE_NAME = HOMEDIR + SEPARATOR + DEFAULT_FILE;

    private File currentFile = new File(FILE_NAME);
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

    /**
     * 
     * @param file
     */
    public void setFile(final File file) {
        if (file.getParentFile().exists()) {
            this.currentFile = file;
        } else {
            throw new IllegalArgumentException("The file passed as an argument does not exist");
        }
    }

    /**
     * 
     * @return s
     */
    public File getFile() {
        return this.currentFile;
    }

    /**
     * 
     * @return s
     */
    public String getPath() {
       return this.currentFile.getPath();
    }

    /**
     * 
     * @param text
     * @throws IOException
     */
    public void writeOnFile(final String text) throws IOException {
        try (PrintStream out = new PrintStream(this.currentFile)) {
            out.println(text);
        }
    }
}
