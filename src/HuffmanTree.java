import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {
    public Node root; // the actuall tree is unnecessary once we have the codes
    private Map<Integer, String> codes = new HashMap<Integer, String>(); 
    private Map<String, Integer> values = new HashMap<String, Integer>(); // reverse of codes

    private void initialiseTree(int[] counts) {
        int length = counts.length;

        // create forest
        PriorityQueue<Node> forest = new PriorityQueue<Node>();
        for (int i = 0; i < length; i++) {
            int count = counts[i];
            if (count == 0) continue;

            forest.add(new Node(i, counts[i]));
        }

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
     * @param {int[]} counts frequency of each character in ASCII order (0-256)
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
        Scanner in = Helper.initialiseScanner(codeFile);

        while (in.hasNextLine()) codes.put(
            Integer.parseInt(in.nextLine().trim()), 
            in.nextLine().trim()
        );
        in.close();
        
        initialiseValues();
    }

    /** Writes tree to a text file in pairs of lines (ASCII code, then Huffman code) */
    public void write(String filename) {
        PrintWriter out = Helper.initialiseWriter(filename);

        // write codes
        codes.forEach((value, code) -> {
            out.println(value);
            out.println(code);
        });

        out.close();
    }

    /** Adding padding for bit file */
    private void pad(BitOutputStream out) { for (int i = 0; i < 7; i++) out.writeBit(0); }

    /** Write encoded character */
    private void encode(BitOutputStream out, int value) {
        for (char bit : codes.get(value).toCharArray()) out.writeBit(bit == '1' ? 1 : 0);
    }

    /** Encodes text file into binary */
    public void encode(BitOutputStream out, String filename) {
        Scanner in = Helper.initialiseScanner(filename);

        while (in.hasNext()) encode(out, in.next().charAt(0));

        encode(out, 256); // write EOF marker
        pad(out); // to avoid not reading parts
        in.close();
    }

    public void decode(BitInputStream in, String outFile) {
        PrintWriter out = Helper.initialiseWriter(outFile);

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
}}