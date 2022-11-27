package StackAndQueue.HomeWork;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 验证随机队列的正确性。作业链接：https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            String item = randomizedQueue.dequeue();
            StdOut.println(item);
        }
    }
}
