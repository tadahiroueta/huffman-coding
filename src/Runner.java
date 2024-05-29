import java.util.Scanner;
import java.util.regex.Pattern;

public class Runner {

    /** Encode and decode */
    private static void testHuffman(String filename) {
        HuffmanCompressor.compress(filename);

        String prefix = Helper.getFilenamePrefix(filename);
        HuffmanCompressor.expand(prefix + ".code", prefix + ".short");
    }

    /**Encode and decode */
    private static void testMoveToFront(String filename) {
        MoveToFront.encode(filename);
        MoveToFront.decode(Helper.getFilenamePrefix(filename) + ".front");
    }

    /** Encode and decode */
    private static void testBurrowsWheeler(String filename) {
        BurrowsWheeler.encode(filename);
        BurrowsWheeler.decode(Helper.getFilenamePrefix(filename) + ".burrows");
    }

    /** Encode and decode */
    private static void testTwo(String filename) {
        MoveToFront.encode(filename);
        String prefix = Helper.getFilenamePrefix(filename);
        testHuffman(prefix + ".front");
        MoveToFront.decode(prefix + ".new");
    }

    public static void main(String[] args) {
        // testHuffman("War and Peace.txt"); // huffman
        // testMoveToFront("War and Peace.txt"); // move-to-front
        // testTwo("War and Peace.txt"); // huffman + move-to-front
        // testBurrowsWheeler("short.txt"); // burrows-wheeler - large time complexity
    }
}
