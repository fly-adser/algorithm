package SortAlgorithm;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Client {

    public static void main(String[] args) {
        Item[] items = new Item[10];
        for (int i=0; i<items.length; i++) {
            int value = StdRandom.uniformInt(100);
            Item item = new Item(value);
            items[i]  = item;
        }

        SortAlgorithm sortAlgorithm = new SortAlgorithm();
        StdRandom.shuffle(items);
        sortAlgorithm.selectSort(items);
        StdOut.println("选择排序： ");
        printItms(items);

        StdRandom.shuffle(items);
        sortAlgorithm.insertSort(items);
        StdOut.println("插入排序： ");
        printItms(items);

        StdRandom.shuffle(items);
        sortAlgorithm.shellSort(items);
        StdOut.println("插入排序： ");
        printItms(items);

        StdRandom.shuffle(items);
        sortAlgorithm.mergeSort(items);
        StdOut.println("归并排序： ");
        printItms(items);

        StdRandom.shuffle(items);
        sortAlgorithm.quickSort(items);
        StdOut.println("快速排序： ");
        printItms(items);
    }

    public static void printItms(Item[] items) {
        for (int i=0; i<items.length; i++) {
            StdOut.print(items[i].getValue());
            StdOut.print(", ");
        }

        StdOut.println();
    }
}
