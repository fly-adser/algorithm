package SymbolTable.BST;

import SymbolTable.BST.BST;
import edu.princeton.cs.algs4.StdOut;

public class client {

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        bst.put(1,1);
        bst.put(2,2);
        bst.put(3,3);
        bst.put(4,4);
        bst.put(5,5);
        bst.put(6,6);

        StdOut.println("size of bst: " + bst.size());
        StdOut.println("value of 3: " + bst.get(3));
        StdOut.println("floor of 7: " + bst.floor(7));
        StdOut.println("rank of 5: " + bst.rank(5));
        bst.deleteMin();
        bst.delete(3);
    }
}
