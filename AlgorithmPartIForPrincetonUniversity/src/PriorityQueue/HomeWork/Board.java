package PriorityQueue.HomeWork;

import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;

public class Board {

    private final int[][] board;
    private final int size;
    private static final int BLANK = 0;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.size  = tiles.length;
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(tiles[i], 0, board[i], 0, size);
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(" " + board[i][j]);
            }
            s.append("\n");
        }
        String string = s.toString();
        return string;
    }

    // board dimension n
    public int dimension() {
        return this.size;
    }

    // 二维坐标变一维坐标
    private int getIndex(int i, int j) {
        return i * size + j + 1;
    }

    // 在目标结果中，值所对应的行数
    private int getRow(int value) {
        return (value - 1) / size;
    }

    // 在目标结果中，值所对应的列数
    private int getCol(int value) {
        return (value - 1) % size;
    }

    // number of tiles out of place
    public int hamming() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != BLANK && board[i][j] != getIndex(i, j)) result += 1;
            }
        }

        return result;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != BLANK && board[i][j] != getIndex(i, j)) {
                    int righti = getRow(board[i][j]);
                    int rightj = getCol(board[i][j]);
                    result     = result + Math.abs(i - righti) + Math.abs(j - rightj);
                }
            }
        }

        return result;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != BLANK && board[i][j] != getIndex(i, j)) return false;
            }
        }

        return true;
    }

    private int[][] copy() {
        int[][] newBlock = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, newBlock[i], 0, size);
        }

        return newBlock;
    }

    private Board swap(int i1, int j1, int i2, int j2) {
        int[][] newBlock = copy();
        int tmp          = newBlock[i1][j1];
        newBlock[i1][j1] = newBlock[i2][j2];
        newBlock[i2][j2] = tmp;

        return new Board(newBlock);
    }

    public Board twin() {
        int i1 = 0, j1 = 0, i2 = 1, j2 = 1;
        if (board[i1][j1] == BLANK) {
            i1 = 1;
            j1 = 0;
        }
        if (board[i2][j2] == BLANK) {
            i2 = 1;
            j2 = 0;
        }

        Board newBoard = swap(i1, j1, i2, j2);
        return newBoard;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return true;

        if (y.getClass().isInstance(this)) {
            Board boardY = (Board) y;
            if (boardY.size != this.size) return false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (this.board[i][j] != boardY.board[i][j]) return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == BLANK) {
                    if (i > 0) {
                        Board upBoard = swap(i, j, i - 1, j);
                        boards.add(upBoard);
                    }

                    if (i < size - 1) {
                        Board lowBoard = swap(i, j, i + 1, j);
                        boards.add(lowBoard);
                    }

                    if (j > 0) {
                        Board leftBoard = swap(i, j, i, j - 1);
                        boards.add(leftBoard);
                    }

                    if (j < size - 1) {
                        Board rightBoard = swap(i, j, i, j + 1);
                        boards.add(rightBoard);
                    }
                }
            }
        }

        return boards;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] boards = {{1, 2, 6}, {4, 5, 3}, {7, 0, 8}};
        Board board    = new Board(boards);
        StdOut.println(board.toString());
        StdOut.println(board.dimension());
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        StdOut.println(board.isGoal());
        Iterable<Board> neibors = board.neighbors();
        for (Board b: neibors) {
            StdOut.println(b.toString());
        }
    }
}