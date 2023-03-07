public class Runner {
    public static void main(String[] args) {
        HuffmanCompressor.compress("short.txt");
        HuffmanCompressor.expand("short.code", "short.new");
    }
}
