package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

/**
 * 
 * This class models a controller that can store String data on the file set as the current. 
 */
public class Controller {

    private static final String DIR = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "output.txt";
    private static final String FILE_NAME = DIR + SEPARATOR + DEFAULT_FILE;

    private File currentFile = new File(FILE_NAME);

    /**
     * Allows to set the current file.
     * 
     * @param file
     *          the file to be set as the current one
     */
    public void setFile(final File file) {
        if (file.getParentFile().exists()) {
            this.currentFile = file;
        } else {
            throw new IllegalArgumentException("The file passed as an argument does not exist");
        }
    }

    /**
     * Returns the current file controlled by the Controller.
     * 
     * @return the current file
     */
    public File getFile() {
        return this.currentFile;
    }

    /**
     * Converts this abstract pathname into a pathname string. 
     * The resulting string uses the default name-separator character to separate the names in the name sequence.
     * 
     * @return the path of the current file in the file system
     */
    public String getPath() {
       return this.currentFile.getPath();
    }

    /**
     * Allows to store the text passed as a parameter on the current file.
     * 
     * @param text
     *          the text that must be stored on the current file
     * @throws IOException in case of an I/O error
     */
    public void writeOnFile(final String text) throws IOException {
        FileUtils.writeStringToFile(this.getFile(), text, Charset.defaultCharset());
    }
}
