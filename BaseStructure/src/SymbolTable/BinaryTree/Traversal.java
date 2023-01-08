package SymbolTable.BinaryTree;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Traversal {

    /**
     * 递归前序遍历
     * @param root
     */
    public void recurPreOrder(TreeNode root) {
        if (root == null) return;

        System.out.print(root.data + " ");
        recurPreOrder(root.left);
        recurPreOrder(root.right);
    }

    /**
     * 递归中序遍历
     * @param root
     */
    public void recurInOrder(TreeNode root) {
        if (root == null) return;

        recurInOrder(root.left);
        System.out.print(root.data + " ");
        recurInOrder(root.right);
    }

    /**
     * 递归后序遍历
     * @param root
     */
    public void recurPostOrder(TreeNode root) {
        if (root == null) return;

        recurPostOrder(root.left);
        recurPostOrder(root.right);
        System.out.print(root.data + " ");
    }

    /**
     * 迭代前序遍历
     * @param root
     */
    public void iteraPreOrder(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode     = root;

        while (currNode != null || !stack.empty()) {
            while (currNode != null) {
                System.out.print(currNode.data + " ");
                stack.push(currNode);
                currNode = currNode.left;
            }

            if (!stack.isEmpty()) {
                currNode = stack.pop();
                currNode = currNode.right;
            }
        }
    }

    /**
     * 迭代中序遍历
     * @param root
     */
    public void iteraInOrder(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode     = root;

        while (currNode != null || !stack.isEmpty()) {
            while (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }

            if (!stack.isEmpty()) {
                currNode = stack.pop();
                System.out.print(currNode.data + " ");
                currNode = currNode.right;
            }
        }
    }

    /**
     * 迭代后序遍历
     * @param root
     */
    public void iteraPostOrder(TreeNode root) {
        if (root == null) return;
        TreeNode currNode = root;
        TreeNode lastNode = null;
        Stack<TreeNode> stack = new Stack<>();

        while (currNode!=null || !stack.isEmpty()) {
            while (currNode!=null) {
                stack.push(currNode);
                currNode = currNode.left;
            }

            if (!stack.isEmpty()) {
                currNode = stack.pop();
                if (currNode.right == null || currNode.right == lastNode) {
                    System.out.print(currNode.data + " ");
                    lastNode = currNode;
                    currNode = null;
                } else {
                    stack.push(currNode);
                    currNode = currNode.right;
                }
            }
        }
    }

    /**
     * 迭代层次遍历
     * @param root
     */
    public void iteraLevelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            System.out.print(currNode.data + " ");

            if (currNode.left != null) queue.add(currNode.left);
            if (currNode.right != null) queue.add(currNode.right);
        }
    }

    /**
     * mirror 前序遍历
     * @param root
     */
    public void mirrorPreOrder(TreeNode root) {
        if (root==null) return;
        TreeNode curr = root, proc = null;

        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.data + " ");
                curr = curr.right;
            } else {
                proc = curr.left;
                while (proc.right!=null && proc.right!=curr) proc = proc.right;
                if (proc.right==curr) {
                    proc.right = null;
                    curr       = curr.right;
                } else {
                    System.out.print(curr.data + " ");
                    proc.right = curr;
                    curr       = curr.left;
                }
            }
        }
    }

    /**
     * mirror 中序遍历
     * @param root
     */
    public void mirrorInOrder(TreeNode root) {
        if (root==null) return;
        TreeNode curr = root, proc = null;

        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.data + " ");
                curr = curr.right;
            } else {
                proc = curr.left;
                while (proc.right!=null && proc.right!=curr) proc = proc.right;
                if (proc.right==curr) {
                    System.out.print(curr.data + " ");
                    proc.right = null;
                    curr       = curr.right;
                } else {
                    proc.right = curr;
                    curr       = curr.left;
                }
            }
        }
    }

    /**
     * mirror 后序遍历
     * @param root
     */
    public void mirrorPostOrder(TreeNode root) {
        if (root==null) return;
        TreeNode curr = root, proc = null;

        while (curr != null) {
            if (curr.left == null) {
                curr = curr.right;
            } else {
                proc = curr.left;
                while (proc.right != null && proc.right != curr) proc = proc.right;
                if (proc.right == curr) {
                    proc.right = null;
                    mirrorsPrint(curr.left);
                    curr       = curr.right;
                } else {
                    proc.right = curr;
                    curr       = curr.left;
                }
            }
        }

        mirrorsPrint(root);
    }

    public void mirrorsPrint(TreeNode root) {
        TreeNode head = reverseList(root);
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.right;
        }

        reverseList(head);
    }

    public TreeNode reverseList(TreeNode root) {
        if (root==null || root.right==null) return root;
        TreeNode pre = null, cur = root, nxt = cur.right;
        while (cur != null) {
            cur.right = pre;
            pre       = cur;
            cur       = nxt;
            if (cur != null) nxt = cur.right;
        }

        return pre;
    }
}
