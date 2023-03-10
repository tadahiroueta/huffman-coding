import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class MoveToFront {
    /** Basically change the order of ASCII characters multiple times to keep the code for the character near zero. It will save a .forward text file */
    public static void encode(String filename) {
        Scanner in = Helper.initialiseScanner(filename);
        PrintWriter out = Helper.initialiseWriter(Helper.getFilenamePrefix(filename) + ".front");
        
        // starts off as ASCII
        Stack<Character> alphabet = new Stack<Character>();
        for (int i = 255; i >= 0; i--) alphabet.push((char) i);
        Stack<Character> beta = new Stack<Character>();
        for (int i = 255; i >= 0; i--) beta.push((char) i);

        while (in.hasNext()) {
            char character = in.next().charAt(0);
            int position = alphabet.indexOf(character);

            out.write((char) (255 - position));

            // move to front
            alphabet.remove(position);
            alphabet.add(character);
        }
        in.close();
        out.close();
    }

    /** It will save a .back file */
    public static void decode(String filename) {
        Scanner in = Helper.initialiseScanner(filename);
        PrintWriter out = Helper.initialiseWriter(Helper.getFilenamePrefix(filename) + ".back");

        // starts off as ASCII
        Stack<Character> alphabet = new Stack<Character>();
        for (int i = 255; i >= 0; i--) alphabet.push((char) i);

        while (in.hasNext()) {
            int position = 255 - in.next().charAt(0);
            char decoded = alphabet.get(position);

            out.write(decoded);

            // move to front
            alphabet.remove(position);
            alphabet.add(decoded);
        }
        in.close();
        out.close();
    }
}
