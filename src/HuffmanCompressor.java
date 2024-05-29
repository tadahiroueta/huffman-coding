import java.util.Scanner;

public class HuffmanCompressor {
    public static void compress(String filename) {
        Scanner in = Helper.initialiseScanner(filename);
        
        int[] frequencies = new int[257];
        while (in.hasNext()) frequencies[in.next().charAt(0)]++;
        frequencies[256] = 1; // for EOF
        
        HuffmanTree tree = new HuffmanTree(frequencies);
        String prefix = Helper.getFilenamePrefix(filename);
        // TreePrinter.printTree(tree.root); // inpractical and useless for large txt files
        tree.write(prefix + ".code");
        tree.encode(new BitOutputStream(prefix + ".short"), filename);    

        in.close();
    }

    public static void expand(String codeFile, String filename) {
        String prefix = Helper.getFilenamePrefix(filename);
        HuffmanTree tree = new HuffmanTree(codeFile);
        tree.decode(new BitInputStream(filename), prefix + ".new");
}}