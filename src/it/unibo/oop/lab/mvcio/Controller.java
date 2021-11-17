package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    static final String DIR = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "output.txt";
    private static final String FILE_NAME = DIR + SEPARATOR + DEFAULT_FILE;

    private File currentFile = new File(FILE_NAME);

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
