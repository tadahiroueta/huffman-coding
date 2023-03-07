import java.util.Scanner;

public class HuffmanCompressor {
    public static void compress(String filename) {
        String name = filename.substring(0, filename.lastIndexOf('.')); // remove txt extension
        Scanner in = new Scanner(name + ".txt");
        
        int[] frequencies = new int[256];
        while (in.hasNextLine()) for (char character : in.nextLine().toCharArray()) frequencies[character]++;
        in.close();

        HuffmanTree tree = new HuffmanTree(frequencies);
        tree.write(name + ".code");
        tree.encode(new BitOutputStream(name + ".short"), name + ".txt");    
    }

    public static void expand(String codeFile, String filename) {
        String name = filename.substring(0, filename.lastIndexOf('.')); // remove txt extension
        HuffmanTree tree = new HuffmanTree(codeFile);
        tree.decode(new BitInputStream(name + ".short"), name + ".new");
}}