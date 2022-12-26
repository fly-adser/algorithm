package SymbolTable.AVL;

public class client {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, -1);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);

        System.out.println("Preorder traversal of "+ "constructed tree is : ");
        tree.preOrderTraversal(tree.root);

        tree.root = tree.deleteNode(tree.root, 10);
        System.out.println("Preorder traversal after "+ "deletion of 10 :");
        tree.preOrderTraversal(tree.root);
    }
}
