package SymbolTable.RedBlackBST;

public class RedBlackTree {
    private static boolean BLACK = true;
    private static boolean RED   = false;
    public TreeNode root;

    private static class NilNode extends TreeNode {
        public NilNode() {
            super(0);
            this.color = BLACK;
        }
    }

    private void replaceParentsChild(TreeNode parent, TreeNode oldChild, TreeNode newChild) {
        if (parent == null) root = newChild;
        else if (parent.left == oldChild) parent.left = newChild;
        else if (parent.right == oldChild) parent.right = newChild;
        else throw new IllegalStateException("Node is not a child of its parent!");

        if (newChild != null) newChild.parent = parent;
    }

    private void rightRotate(TreeNode treeNode) {
        TreeNode parent    = treeNode.parent;
        TreeNode leftChild = treeNode.left;

        treeNode.left      = leftChild.right;
        if (leftChild.right != null) leftChild.right.parent = treeNode;

        leftChild.right    = treeNode;
        treeNode.parent    = leftChild;
        replaceParentsChild(parent, treeNode, leftChild);
    }

    private void leftRotate(TreeNode treeNode) {
        TreeNode parent     = treeNode.parent;
        TreeNode rightChild = treeNode.right;

        treeNode.right      = rightChild.left;
        if (rightChild.left != null) rightChild.left.parent = treeNode;

        rightChild.left     = treeNode;
        treeNode.parent     = rightChild;
        replaceParentsChild(parent, treeNode, rightChild);
    }

    public TreeNode searchNode(int key) {
        TreeNode treeNode = root;
        while (treeNode != null) {
            if (key == treeNode.data) return treeNode;
            else if (key < treeNode.data) treeNode = treeNode.left;
            else treeNode = treeNode.right;
        }

        return null;
    }

    private TreeNode getUncle(TreeNode parent) {
        TreeNode grandParent = parent.parent;
        if (grandParent == null) throw new IllegalStateException("No uncle can get here!");

        if (grandParent.left == parent) return grandParent.right;
        else if (grandParent.right == parent) return grandParent.left;
        else throw new IllegalStateException("Parent is not a chile of its grandParent!");
    }

    public void fixRedBlackPropertiesAfterInsert(TreeNode treeNode) {
        TreeNode parent = treeNode.parent;

        /* 1. parent is null */
        if (parent == null) return;

        /* 2. parent is black */
        if (parent.color == BLACK) return;

        TreeNode grandParent = parent.parent;
        /* 3. parent is root */
        if (grandParent == null) {
            parent.color = BLACK;
            return;
        }

        TreeNode uncle = getUncle(parent);
        /* 4. uncle is red */
        if (uncle != null && uncle.color == RED) {
            grandParent.color = RED;
            parent.color      = BLACK;
            uncle.color       = BLACK;
            fixRedBlackPropertiesAfterInsert(grandParent);
        } else if (parent == grandParent.left) {
            /* 5. uncle is black and node is left-right" inner child" of its grandparent */
            if (treeNode == parent.right) {
                leftRotate(parent);
                parent = treeNode;
            }

            /* 6. uncle is black and node is left-left "outer child" of its grandparent */
            rightRotate(grandParent);
            parent.color      = BLACK;
            grandParent.color = RED;
        } else {
            /* 5. uncle is black and node is right-left "inner child" of its grandparent */
            if (treeNode == parent.left) {
                rightRotate(parent);
                parent = treeNode;
            }

            /* 6. uncle is black and node is right-right "outer child" of its grandparent */
            leftRotate(grandParent);
            parent.color      = BLACK;
            grandParent.color = RED;
        }
    }

    public void insert(int key) {
        TreeNode node   = root;
        TreeNode parent = null;

        while (node != null) {
            parent = node;
            if (key < node.data) node = node.left;
            else if (key > node.data) node = node.right;
            else throw new IllegalArgumentException("BST has already a node woth key: " + key);
        }

        TreeNode newNode = new TreeNode(key);
        newNode.color    = RED;
        if (parent == null) root = newNode;
        else if (key < parent.data) parent.left = newNode;
        else parent.right = newNode;

        newNode.parent = parent;
        fixRedBlackPropertiesAfterInsert(newNode);
    }

    private TreeNode deleteNodeWithZeroOrOneChild(TreeNode node) {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left;
        } else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right;
        } else {
            TreeNode newChild = node.color == BLACK ? new NilNode() : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private TreeNode getSibling(TreeNode treeNode) {
        TreeNode parent = treeNode.parent;
        if (parent == null) throw new IllegalStateException("the node has no parent!");

        if (treeNode == parent.left) return parent.right;
        else if (treeNode == parent.right) return parent.left;
        else throw new IllegalStateException("parent is not a child of its grandparent!");
    }

    private boolean isBLACK(TreeNode treeNode) {
        return treeNode == null || treeNode.color == BLACK;
    }

    private void handleRedSibling(TreeNode treeNode, TreeNode sibling) {
        sibling.color         = BLACK;
        treeNode.parent.color = RED;

        if (treeNode == treeNode.parent.left) {
            leftRotate(treeNode.parent);
        } else {
            rightRotate(treeNode.parent);
        }
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(TreeNode treeNode, TreeNode sibling) {
        boolean nodeIsLeftChild = treeNode == treeNode.parent.left;

        if (nodeIsLeftChild && isBLACK(sibling.right)) {
            sibling.left.color  = BLACK;
            sibling.color       = RED;
            leftRotate(sibling);
            sibling             = treeNode.parent.right;
        } else if (!nodeIsLeftChild && isBLACK(sibling.left)) {
            sibling.right.color = BLACK;
            sibling.color       = RED;
            rightRotate(sibling);
            sibling             = treeNode.parent.left;
        }

        sibling.color         = treeNode.parent.color;
        treeNode.parent.color = BLACK;
        if (nodeIsLeftChild) {
            sibling.right.color = BLACK;
            leftRotate(treeNode.parent);
        } else {
            sibling.left.color  = BLACK;
            rightRotate(treeNode.parent);
        }
    }

    private void fixRedBlackPropertiesAfterDelete(TreeNode treeNode) {
        /* 1. Case 1: Examined node is root, end of recursion */
        if (treeNode == root) return;

        TreeNode sibling = getSibling(treeNode);

        /* 2. Red sibling */
        if (sibling.color == RED) {
            handleRedSibling(treeNode, sibling);
            sibling = getSibling(treeNode);
        }

        /* 3. Cases 3+4: Black sibling with two black children */
        if (isBLACK(sibling.left) && isBLACK(sibling.right)) {
            sibling.color = RED;

            /* Case 3: Black sibling with two black children + red parent */
            if (treeNode.parent.color == RED) {
                treeNode.parent.color = BLACK;
            } else {
                /* Case 4: Black sibling with two black children + black parent */
                fixRedBlackPropertiesAfterDelete(treeNode.parent);
            }
        } else {
            /* Case 5+6: Black sibling with at least one red child */
            handleBlackSiblingWithAtLeastOneRedChild(treeNode, sibling);
        }
    }

    public void delete(int key) {
        TreeNode node = root;

        while (node != null && node.data != key) {
            if (key < node.data) node = node.left;
            else node = node.right;
        }

        if (node == null) return;
        TreeNode moveUpNode;
        boolean deleteNodeColor;

        if (node.left == null || node.right == null) {
            moveUpNode      = deleteNodeWithZeroOrOneChild(node);
            deleteNodeColor = node.color;
        } else {
            TreeNode inOrderSuccessor = findMinNode(node.right);
            node.data                 = inOrderSuccessor.data;
            moveUpNode                = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
            deleteNodeColor           = moveUpNode.color;
        }

        if (deleteNodeColor == BLACK) {
            fixRedBlackPropertiesAfterDelete(moveUpNode);
            if (moveUpNode.getClass() == NilNode.class) replaceParentsChild(moveUpNode.parent, moveUpNode, null);
        }
    }

    public void preOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;

        System.out.println(treeNode.data);
        preOrderTraversal(treeNode.left);
        preOrderTraversal(treeNode.right);
    }
}
