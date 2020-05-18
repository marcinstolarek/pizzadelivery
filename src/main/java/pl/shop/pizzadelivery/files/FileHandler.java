package pl.shop.pizzadelivery.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Read file, print it as String and close file. Skip lines begins with '#' - it is comment
 */
public abstract class FileHandler {

    /**
     * Get whole text from file instead of comments (line begins with '#')
     * @param fileName - path to file
     * @return content of file (if file and content exist)
     */
    protected static String getFileContent(String fileName) {
        return printBuffer(getFileBuffer(fileName));
    }

    /**
     * Get buffer from file
     * @param fileName - path to file
     * @return buffer or null if file wasn't found
     */
    private static BufferedReader getFileBuffer(String fileName) {
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return buff;
    }

    /**
     * Printing buffer from file. When file is read, it will be closed.
     * Skip lines begins with '#' - it is comment
     * @param buff - buffer from file
     * @return content of file
     */
    private static String printBuffer(BufferedReader buff) {
        String line = null;
        StringBuilder data = new StringBuilder();
        do {
            try {
                line = buff.readLine();
                if (line != null && line.charAt(0) != '#') { // skip comments
                    data.append(line);
                    data.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }while(line != null);
        try { // close file
            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}
