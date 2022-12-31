package SymbolTable.RedBlackBST;

public class client {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(3);
        tree.insert(2);
        tree.insert(5);
        tree.insert(6);
        tree.insert(1);

        System.out.println("Preorder traversal of "+ "constructed tree is : ");
        tree.preOrderTraversal(tree.root);

        tree.delete(5);
        System.out.println("Preorder traversal after "+ "deletion of 5 :");
        tree.preOrderTraversal(tree.root);
    }
}
