package SymbolTable.AVL;

public class AVLTree {
    public TreeNode root;

    private int height(TreeNode treeNode) {
        if (treeNode == null) return 0;

        return treeNode.height;
    }

    public int max(int a, int b) {
        return Math.max(a, b);
    }

    public TreeNode rightRotate(TreeNode treeNode) {
        TreeNode tl = treeNode.left;
        TreeNode lr = tl.right;

        tl.right      = treeNode;
        treeNode.left = lr;

        treeNode.height = max(height(treeNode.left), height(treeNode.right)) + 1;
        tl.height       = max(height(tl.left), height(tl.right)) + 1;

        return tl;
    }

    public TreeNode leftRatote(TreeNode treeNode) {
        TreeNode tr = treeNode.right;
        TreeNode rl = tr.left;

        tr.left        = treeNode;
        treeNode.right = rl;

        treeNode.height = max(height(treeNode.left), height(treeNode.right)) + 1;
        tr.height       = max(height(tr.left), height(tr.right)) + 1;

        return tr;
    }

    public int getBalanceFactor(TreeNode treeNode) {
        if (treeNode == null) return 0;

        return height(treeNode.left) - height(treeNode.right);
    }

    public TreeNode insert(TreeNode treeNode, int key) {
        /* 1. Perform the normal BST insertion */
        if (treeNode == null) return new TreeNode(key);

        if (key < treeNode.data) treeNode.left = insert(treeNode.left, key);
        else if (key > treeNode.data) treeNode.right = insert(treeNode.right, key);
        else return treeNode;

        /* 2. Update height of this ancestor node */
        treeNode.height = max(height(treeNode.left), height(treeNode.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether this node became unbalance */
        int balance = getBalanceFactor(treeNode);

        /* If this node become unbalance, then there are 4 cases*/
        /* a. left-left case */
        if (balance > 1 && key < treeNode.left.data) {
            return rightRotate(treeNode);
        }

        /* b. left-right case */
        if (balance > 1 && key > treeNode.left.data) {
            treeNode.left = leftRatote(treeNode.left);
            return rightRotate(treeNode);
        }

        /* c. right-right case */
        if (balance < -1 && key > treeNode.right.data) {
            return leftRatote(treeNode);
        }

        /* d. right-left case */
        if (balance < -1 && key < treeNode.right.data) {
            treeNode.right = rightRotate(treeNode.right);
            return leftRatote(treeNode);
        }

        return treeNode;
    }

    public TreeNode minValueNode(TreeNode treeNode) {
        TreeNode currNode = treeNode;
        while (currNode.left != null) {
            currNode = currNode.left;
        }

        return currNode;
    }

    public TreeNode deleteNode(TreeNode treeNode, int key) {
        if (treeNode == null) return null;

        if (key < treeNode.data) treeNode.left = deleteNode(treeNode.left, key);
        else if (key > treeNode.data) treeNode.right = deleteNode(treeNode.right, key);
        else {
            if (treeNode.left != null && treeNode.right != null) {
                TreeNode minNode = minValueNode(treeNode.right);
                treeNode.data    = minNode.data;
                treeNode.right   = deleteNode(treeNode.right, minNode.data);
            } else {
                if (treeNode.left == null) {
                    treeNode = treeNode.right;
                } else {
                    treeNode = treeNode.left;
                }
            }
        }

        if (treeNode == null) return null;
        treeNode.height = max(height(treeNode.left), height(treeNode.right)) + 1;
        int balance     = getBalanceFactor(treeNode);

        /* a. left-left case */
        if (balance > 1 && getBalanceFactor(treeNode.left) >= 0) {
            return rightRotate(treeNode);
        }

        /* b. left-right case */
        if (balance > 1 && getBalanceFactor(treeNode.left) < 0) {
            treeNode.left = leftRatote(treeNode.left);
            return rightRotate(treeNode);
        }

        /* c. right-right case */
        if (balance < -1 && getBalanceFactor(treeNode.right) <= 0) {
            return leftRatote(treeNode);
        }

        /* d. right-left case */
        if (balance < -1 && getBalanceFactor(treeNode.right) > 0) {
            treeNode.right = rightRotate(treeNode.right);
            return leftRatote(treeNode);
        }

        return treeNode;
    }

    public void preOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;

        System.out.println(treeNode.data);
        preOrderTraversal(treeNode.left);
        preOrderTraversal(treeNode.right);
    }
}
