package UnionFind.HomeWork;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;

/**
 * 通过仿真模拟计算渗透概率。作业链接：https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php
 */
public class PercolationStats {
    private double[] ptArray;
    private Percolation percolation;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal parameter:" + n);
        }

        if (trials <= 0) {
            throw new IllegalArgumentException("Illegal parameter:" + trials);
        }

        ptArray = new double[trials];

        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int randNum = StdRandom.uniformInt(1,n * n + 1);
                int row     = 1;
                int col     = 1;
                if (randNum % n == 0) {
                    row     = randNum / n;
                    col     = n;
                } else {
                    row     = randNum / n + 1;
                    col     = randNum % n;
                }

                percolation.open(row, col);
            }

            ptArray[i] =  (percolation.numberOfOpenSites() * 1.0) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(ptArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddevp(ptArray);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double s = StdStats.stddev(ptArray);
        double x = StdStats.mean(ptArray);

        return x - 1.96 * s / Math.sqrt(ptArray.length * 1.0);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double s = StdStats.stddev(ptArray);
        double x = StdStats.mean(ptArray);

        return x + 1.96 * s / Math.sqrt(ptArray.length * 1.0);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n      = 0;
        int trials = 0;
        while (!StdIn.isEmpty()) {
            if (n == 0) {
                n = Integer.parseInt(StdIn.readString());
                continue;
            }
            trials = Integer.parseInt(StdIn.readString());
            break;
        }

        StdOut.println("n=" + n + "; trials=" + trials);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("mean =  " + percolationStats.mean());
        StdOut.println("stddev =  " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");
    }
}
