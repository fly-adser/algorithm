package StackAndQueue;

import java.util.Iterator;

/**
 * 1.使用List实现队列；2.通过泛型让队列支持不同的数据类型；3.通过iterator接口实现迭代器
 */
public class QueueByListV2<T> implements Iterable<T>{
    private class Node {
        T item;
        Node next;
    }

    private Node first, last;

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T item) {
        Node oldLast = last;
        last         = new Node();
        last.item    = item;
        last.next    = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public T pop() {
        T item = first.item;
        first  = first.next;
        if (isEmpty()) last = null;

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
            return first != null;
        }

        @Override
        public T next() {
            T item  = current.item;
            current = current.next;

            return item;
        }
    }
}
