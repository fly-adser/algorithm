package SymbolTable;

public class Node<KEY, VALUE> {
    public KEY key;
    public VALUE value;
    public Node left, right;
    public int count;

    public Node(KEY key, VALUE value) {
        this.key   = key;
        this.value = value;
    }
}
