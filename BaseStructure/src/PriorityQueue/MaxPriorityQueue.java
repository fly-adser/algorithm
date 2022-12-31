package PriorityQueue;

public class MaxPriorityQueue<Item extends Comparable<Item>> {
    private Item[] priorityQueue;
    private int size = 0;

    public MaxPriorityQueue(int capacity) {
        priorityQueue = (Item[]) new Comparable[capacity + 1];
    }

    private boolean isLess(int i, int j) {
        return priorityQueue[i].compareTo(priorityQueue[j]) < 0;
    }

    private void exChange(int i, int j) {
        Item item        = priorityQueue[i];
        priorityQueue[i] = priorityQueue[j];
        priorityQueue[j] = item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && isLess(j, j+1)) exChange(j, j+1);
            if (!isLess(k, j)) break;
            exChange(k, j);
            k = j;
        }
    }

    public Item delMax() {
        Item max = priorityQueue[1];
        exChange(1, size--);
        sink(1);
        priorityQueue[size + 1] = null;

        return max;
    }

    private void swim(int k) {
        while (k > 1 && isLess(k / 2, k)) {
            exChange(k / 2, k);
            k = k / 2;
        }
    }

    public void insert(Item item) {
        priorityQueue[++size] = item;
        swim(size);
    }

    public void sort() {
        int N = size;

        for (int k = N / 2; k >= 1; k--) {
            sink(k);
        }

        while (N > 1) {
            exChange(1, N--);
            sink(1);
        }
    }
}