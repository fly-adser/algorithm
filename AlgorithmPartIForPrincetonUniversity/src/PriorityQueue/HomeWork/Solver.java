package PriorityQueue.HomeWork;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private class TreeNode implements Comparable<TreeNode> {
        private final Board board;
        private final TreeNode pre;
        private final int moves;
        private final int priority;

        public TreeNode(Board b, TreeNode pre) {
            this.board = b;
            this.pre   = pre;
            if (pre != null) {
                this.moves    = pre.moves + 1;
                this.priority = this.moves + this.board.manhattan();
            } else {
                this.moves    = 0;
                this.priority = this.moves + this.board.manhattan();
            }
        }

        public TreeNode getPre() {
            return this.pre;
        }

        public int getMoves() {
            return this.moves;
        }

        public Board getBoard() {
            return this.board;
        }

        public int getPriority() {
            return this.priority;
        }

        @Override
        public int compareTo(TreeNode other) {
            if (this.priority == other.priority) {
                return this.board.manhattan() - other.board.manhattan();
            }

            return this.priority - other.priority;
        }
    }

    private TreeNode currentNode;
    private TreeNode currentTwinNode;
    private Stack<Board> stack;
    private final boolean isSolvable;

    public Solver(Board initial) {
        if (initial == null)
            throw new NullPointerException();

        currentNode            = new TreeNode(initial, null);
        MinPQ<TreeNode> minPQ  = new MinPQ<>();
        minPQ.insert(currentNode);

        currentTwinNode        = new TreeNode(initial.twin(), null);
        MinPQ<TreeNode> twinPQ = new MinPQ<>();
        twinPQ.insert(currentTwinNode);

        while (true) {
            currentNode = minPQ.delMin();
            if (currentNode.getBoard().isGoal()) {
                isSolvable = true;
                break;
            } else {
                for (Board it: currentNode.getBoard().neighbors()) {
                    if (currentNode.getPre() == null || !it.equals(currentNode.getPre().getBoard())) {
                        minPQ.insert(new TreeNode(it, currentNode));
                    }
                }
            }

            currentTwinNode = twinPQ.delMin();
            if (currentTwinNode.getBoard().isGoal()) {
                isSolvable = false;
                break;
            } else {
                for (Board it: currentTwinNode.getBoard().neighbors()) {
                    if (currentTwinNode.getPre() == null || !it.equals(currentTwinNode.getPre().getBoard())) {
                        twinPQ.insert(new TreeNode(it, currentTwinNode));
                    }
                }
            }
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (isSolvable()) {
            return currentNode.getMoves();
        } else {
            return -1;
        }
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        stack            = new Stack<>();
        TreeNode nowNode = currentNode;
        while (nowNode != null) {
            stack.push(nowNode.getBoard());
            nowNode = nowNode.getPre();
        }

        return stack;
    }

    public static void main(String[] args) {
        int[][] blocks = new int[3][3];

        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;

        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;

        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 0;

        Board board   = new Board(blocks);
        Solver solver = new Solver(board);

        if (!solver.isSolvable()) {
            System.out.println("this board is can't resolve");
        } else {
            Iterable<Board> bIterable = solver.solution();
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board it : bIterable) {
                System.out.println(it.toString());
            }
        }
    }
}