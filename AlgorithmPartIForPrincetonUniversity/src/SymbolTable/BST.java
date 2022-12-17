package SymbolTable;

public class BST<KEY extends Comparable<KEY>, VALUE> {
    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;

        return x.count;
    }

    public VALUE get(KEY key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo((KEY) x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return (VALUE) x.value;
        }

        return null;
    }

    public void put(KEY key, VALUE value) {
        root = put(root, key, value);
    }

    private Node put(Node x, KEY key, VALUE value) {
        if (x == null) return new Node(key, value);
        int cmp = key.compareTo((KEY) x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    public KEY floor(KEY key) {
        Node x = floor(root, key);
        if (x == null) return null;

        return (KEY) x.key;
    }

    private Node floor(Node x, KEY key) {
        if (x == null) return null;
        int cmp = key.compareTo((KEY) x.key);

        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);

        if (t != null) return t;
        else return x;
    }

    public int rank(KEY key) {
        return rank(key, root);
    }

    private int rank(KEY key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo((KEY) x.key);

        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left  = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    public void delete(KEY key) {
        root = delete(root, key);
    }

    private Node delete(Node x, KEY key) {
        if (x == null) return null;
        int cmp = key.compareTo((KEY) x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            Node t    = x;
            Node min  = x.right;
            while (min.left != null) min = min.left;
            min.right = deleteMin(t.right);
            min.left  = t.left;
        }

        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }
}
