package StackAndQueue.HomeWork;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int size = 0;
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("illegal parameter: " + item);
        }

        if (size == s.length) resize(2 * size);
        s[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is null, you can not remove any item from it!!!");
        }

        int target = StdRandom.uniformInt(size);
        Item item  = s[target];
        swap(target, size-1);
        s[--size]  = null;

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is null, you can not remove any item from it!!!");
        }

        int target = StdRandom.uniformInt(size);
        Item item  = s[target];

        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = s[i];
        }

        s = copy;
    }

    private void swap(int indexA, int indexB) {
        Item item = s[indexA];
        s[indexA] = s[indexB];
        s[indexB] = item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int current;
        private Item[] iters;

        public ArrayIterator() {
            current = size;
            iters   = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iters[i] = s[i];
            }
        }

        @Override
        public boolean hasNext() {
            return current != 0;
        }

        @Override
        public Item next() {
            if (current == 0) {
                throw new NoSuchElementException("the deque is null, you can not get any item from it!!!");
            }

            int target = StdRandom.uniformInt(current);
            Item item  = iters[target];
            iters[target]    = iters[current-1];
            iters[--current] = null;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        Integer a1 = randomizedQueue.dequeue();
        StdOut.println(a1);
        Integer a2 = randomizedQueue.sample();
        StdOut.println(a2);
        Iterator<Integer> iterator = randomizedQueue.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
    }
}
