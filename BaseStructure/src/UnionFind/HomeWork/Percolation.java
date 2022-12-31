package UnionFind.HomeWork;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int edge;
    private int openSize;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal parameter:" + n);
        }

        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }

        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 0; i < n; i++) {
            weightedQuickUnionUF.union(n * n, i);
            weightedQuickUnionUF.union(n * n + 1, n * (n-1) + i);
        }

        edge     = n;
        openSize = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > edge) {
            throw new IllegalArgumentException("Illegal parameter:" + row);
        }
        if (col < 1 || col > edge) {
            throw new IllegalArgumentException("Illegal parameter:" + col);
        }

        if (!isOpen(row, col)) {
            grid[row-1][col-1] = true;

            if (row > 1 && isOpen(row-1, col)) {
                weightedQuickUnionUF.union((row-2) * edge + col - 1, (row - 1) * edge + col -1);
            }

            if (row < edge && isOpen(row+1, col)) {
                weightedQuickUnionUF.union(row * edge + col -1, (row - 1) * edge + col - 1);
            }

            if (col > 1 && isOpen(row, col-1)) {
                weightedQuickUnionUF.union((row - 1) * edge + col - 2, (row - 1) * edge + col - 1);
            }

            if (col < edge && isOpen(row, col+1)) {
                weightedQuickUnionUF.union((row - 1) * edge + col, (row - 1) * edge + col - 1);
            }

            openSize += 1;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > edge) {
            throw new IllegalArgumentException("Illegal parameter:" + row);
        }
        if (col < 1 || col > edge) {
            throw new IllegalArgumentException("Illegal parameter:" + col);
        }

        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > edge) {
            throw new IllegalArgumentException("Illegal parameter:" + row);
        }
        if (col < 1 || col > edge) {
            throw new IllegalArgumentException("Illegal parameter:" + col);
        }

        if (!isOpen(row, col)) {
            return false;
        }

        int pid = weightedQuickUnionUF.find((row - 1) * edge + col - 1);
        int qid = weightedQuickUnionUF.find(edge * edge);

        return pid == qid;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSize;
    }

    // does the system percolate?
    public boolean percolates() {
        int pid = weightedQuickUnionUF.find(edge * edge);
        int qid = weightedQuickUnionUF.find(edge * edge + 1);

        return pid == qid;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
