public class Runner {

    /** 
     * Encode and decode
     * @param filename text file
     */
    private static void test(String filename) {
        HuffmanCompressor.compress(filename);

        String prefix = filename.substring(0, filename.lastIndexOf('.') + 1); // remove txt extension
        HuffmanCompressor.expand(prefix + "code", filename + "short");
    }

    public static void main(String[] args) {
        test("happy hip hop.txt");
        test("short.txt");
        test("Hamlet.txt");
        test("War and Peace.txt");
    }
}
