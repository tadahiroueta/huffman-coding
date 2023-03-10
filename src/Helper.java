import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Helper {
    protected static PrintWriter initialiseWriter(String filename) {
        PrintWriter out;
        try { out = new PrintWriter(filename); }
        catch (FileNotFoundException e) {
            System.out.println("Error creating file for tree.");
            return null;
        }
        return out;
    }

    /** Use next() for next character */
    protected static Scanner initialiseScanner(String filename) {
        Scanner in;
        try { in = new Scanner(new File(filename)); }
        catch (FileNotFoundException e) {
            System.out.println("Error reading " + filename + "."); 
            return null;
        }
        in.useDelimiter("");
        return in;
    }

    protected static String getFilenamePrefix(String filename) { return filename.substring(0, filename.lastIndexOf('.')); }
}
