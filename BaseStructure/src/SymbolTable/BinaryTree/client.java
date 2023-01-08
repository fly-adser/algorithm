package SymbolTable.BinaryTree;

public class client {

    public static void main(String[] args) {
        TreeNode root  = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left      = node2;
        root.right     = node3;
        node2.left     = node4;
        node2.right    = node5;
        node3.left     = node6;

        Traversal traversal = new Traversal();
        System.out.println("--------递归前序遍历--------");
        traversal.recurPreOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------递归中序遍历--------");
        traversal.recurInOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------递归后序遍历--------");
        traversal.recurPostOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------迭代前序遍历--------");
        traversal.iteraPreOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------迭代中序遍历--------");
        traversal.iteraInOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------迭代后序遍历--------");
        traversal.iteraPostOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------迭代层次遍历--------");
        traversal.iteraLevelOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------Mirror前序遍历--------");
        traversal.mirrorPreOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------Mirror中序遍历--------");
        traversal.mirrorInOrder(root);
        System.out.println("--------------------------");

        System.out.println("--------Mirror后序遍历--------");
        traversal.mirrorPostOrder(root);
        System.out.println("--------------------------");
    }
}
