package StackAndQueue;

import java.util.Iterator;

/**
 * 1.使用List实现栈；2.通过泛型支持不同数据类型；3.通过iterator接口实现迭代器
 */
public class StackByListV2<T> implements Iterable<T>{

    private class Node {
        T item;
        Node next;
    }

    public Node first = null;

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T item) {
        Node oldFirst = first;
        first         = new Node();
        first.item    = item;
        first.next    = oldFirst;
    }

    public T pop() {
        T item = first.item;
        first  = first.next;

        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item  = current.item;
            current = current.next;

            return item;
        }
    }
}
