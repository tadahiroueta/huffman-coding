import java.util.Scanner;

public class HuffmanCompressor {
    public static void compress(String filename) {
        String name = filename.substring(0, filename.lastIndexOf('.')); // remove txt extension
        Scanner in = Helper.initialiseScanner(name + ".txt");
        
        int[] frequencies = new int[257];
        while (in.hasNext()) frequencies[in.next().charAt(0)]++;
        frequencies[256] = 1; // for EOF
        
        HuffmanTree tree = new HuffmanTree(frequencies);
        tree.write(name + ".code");
        tree.encode(new BitOutputStream(name + ".short"), name + ".txt");    

        in.close();
    }

    public static void expand(String codeFile, String filename) {
        String name = filename.substring(0, filename.lastIndexOf('.')); // remove txt extension
        HuffmanTree tree = new HuffmanTree(codeFile);
        tree.decode(new BitInputStream(name + ".short"), name + ".new");
}}