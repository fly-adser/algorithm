package StackAndQueue.HomeWork;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node pre;
    }

    private Node head, tail;
    private int size;

    public Deque() {
        head  = new Node();
        tail  = new Node();
        head.next = tail;
        tail.pre  = head;
        size      = 0;
    }

    public boolean isEmpty() {
        return head.next == tail && tail.pre == head;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("illegal parameter: " + item);
        }

        Node oldFirst = head.next;
        Node first    = new Node();
        first.item    = item;
        first.next    = oldFirst;
        oldFirst.pre  = first;
        head.next     = first;
        first.pre     = head;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("illegal parameter: " + item);
        }

        Node oldLast = tail.pre;
        Node last    = new Node();
        last.item    = item;
        oldLast.next = last;
        last.pre     = oldLast;
        last.next    = tail;
        tail.pre     = last;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is null, you can not remove any item from it!!!");
        }

        Item item  = head.next.item;
        Node first = head.next.next;
        head.next  = first;
        first.pre  = head;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("the deque is null, you can not remove any item from it!!!");
        }

        Item item = tail.pre.item;
        Node last = tail.pre.pre;
        last.next = tail;
        tail.pre  = last;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head.next;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public Item next() {
            if (current == tail) {
                throw new NoSuchElementException("the deque is null, you can not get any item from it!!!");
            }

            Item item = current.item;
            current   = current.next;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        Integer a1 = deque.removeFirst();
        Integer a2 = deque.removeLast();
        StdOut.println("a1: " + a1 + "; a2: " + a2);
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
    }
}