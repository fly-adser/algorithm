package SymbolTable.AVL;

public class TreeNode {
    public int data;
    public int height;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.data   = value;
        this.height = 1;
    }
}
