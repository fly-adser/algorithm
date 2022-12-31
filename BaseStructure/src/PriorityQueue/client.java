package PriorityQueue;

import edu.princeton.cs.algs4.StdOut;

public class client {

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> maxPriorityQueue = new MaxPriorityQueue<>(5);
        maxPriorityQueue.insert(3);
        maxPriorityQueue.insert(2);
        maxPriorityQueue.insert(4);
        maxPriorityQueue.insert(1);
        maxPriorityQueue.insert(5);
        maxPriorityQueue.sort();
        while (!maxPriorityQueue.isEmpty()) {
            Integer value = maxPriorityQueue.delMax();
            StdOut.println(value);
        }
    }
}