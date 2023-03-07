import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {
    private Node root; // the actuall tree is unnecessary once we have the codes
    private Map<Integer, String> codes = new HashMap<Integer, String>(); 
    private Map<String, Integer> values = new HashMap<String, Integer>(); // reverse of codes


    /** Initialise PrintWrite object */
    private PrintWriter initialiseWriter(String filename) {
        PrintWriter out;
        try { out = new PrintWriter(filename); }
        catch (FileNotFoundException e) {
            System.out.println("Error creating file for tree.");
            return null;
        }
        return out;
    }

    private Scanner initialiseScanner(String filename) {
        Scanner in;
        try { in = new Scanner(new File(filename)); }
        catch (FileNotFoundException e) {
            System.out.println("Error reading " + filename + "."); 
            return null;
        }
        return in;
    }

    private void initialiseTree(int[] counts) {
        int length = counts.length;

        // create forest
        PriorityQueue<Node> forest = new PriorityQueue<Node>();
        for (int i = 0; i < length; i++) forest.add(new Node(i, counts[i]));

        forest.add(new Node(length, 1)); // add EOF (end of file) marker

        // put it all together in a tree
        while (forest.size() > 1) {
            Node right = forest.remove();
            Node left = forest.remove();
            Node parent = new Node(left, right);
            forest.add(parent);
        }
        root = forest.remove();
    }

    private void initialiseCodes(Node node, String code) {
        if (node.isLeaf()) {
            codes.put(node.value, code);
            return;
        }
        
        // if parent of two
        initialiseCodes(node.left, code + "0");
        initialiseCodes(node.right, code + "1");
    }

    private void initialiseValues() { codes.forEach((value, code) -> values.put(code, value)); }

    /**
     * @param {int[]} counts frequency of each character in ASCII order (0-255)
     */
    public HuffmanTree(int[] counts) {
        initialiseTree(counts);
        initialiseCodes(root, "");
        initialiseValues();
    }

    /**
     * @param {String} codeFile file containing ASCII code and Huffman code pairs
     */
    public HuffmanTree(String codeFile) {
        Scanner in = initialiseScanner(codeFile);

        while (in.hasNextLine()) codes.put(
            Integer.parseInt(in.nextLine().trim()), 
            in.nextLine().trim()
        );
        in.close();
        
        initialiseValues();
    }

    /** Writes tree to a text file in pairs of lines (ASCII code, then Huffman code) */
    public void write(String filename) {
        PrintWriter out = initialiseWriter(filename);

        // write codes
        codes.forEach((value, code) -> {
            out.println(value);
            out.println(code);
        });

        out.close();
    }

    /** Write encoded character */
    private void encode(BitOutputStream out, int value) {
        for (char bit : codes.get(value).toCharArray())
            out.writeBit(bit == '1' ? 1 : 0);
    }

    /** Encodes text file into binary */
    public void encode(BitOutputStream out, String filename) {
        Scanner in = initialiseScanner(filename);
        while (in.hasNextLine()) for (char character : in.nextLine().toCharArray()) encode(out, character);

        encode(out, 256); // write EOF marker
        in.close();
    }

    public void decode(BitInputStream in, String outFile) {
        PrintWriter out = initialiseWriter(outFile);

        String code = "";
        while (true) {
            code += in.readBit();
            if (!values.containsKey(code)) continue; // not full code yet

            int value = values.get(code);
            
            if (value == 256) break; // if EOF marker
            
            out.print((char) value);
            code = "";
        }
        out.close();
    }
}