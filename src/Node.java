public class Node implements Comparable<Node> {
    protected int value;
    protected int weight;
    protected Node left, right;

    public Node(int value, int weight) { 
        this.value = value;
        this.weight = weight;
    }

    public Node(Node left, Node right) { 
        this.left = left;
        this.right = right;
        weight = left.weight + right.weight;
    }

    public boolean isLeaf() { return left == null && right == null; }

    @Override
    public int compareTo(Node other) { return weight - other.weight; } // lightest first

    @Override
    public String toString() { return value + ""; }
}
