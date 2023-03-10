import java.util.Scanner;
import java.util.regex.Pattern;

public class Runner {

    /** 
     * Encode and decode
     * @param filename text file
     */
    private static void testHuffman(String filename) {
        HuffmanCompressor.compress(filename);

        String prefix = filename.substring(0, filename.lastIndexOf('.') + 1); // remove txt extension
        HuffmanCompressor.expand(prefix + "code", filename + "short");
    }

    /**
     * Encode and decode
     * @param filename text file
     */
    private static void testMoveToFront(String filename) {
        MoveToFront.encode(filename);
        MoveToFront.decode(Helper.getFilenamePrefix(filename) + ".front");
    }

    public static void main(String[] args) {
        // testMoveToFront("War and Peace.txt");
        testHuffman("War and Peace.txt");
    }
}
