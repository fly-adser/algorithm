package StackAndQueue;

import java.util.Iterator;

/**
 * 1.使用List实现队列；2.通过泛型让队列支持不同的数据类型；3.通过iterator接口实现迭代器
 */
public class QueueByListV2<Item> implements Iterable<Item>{
    private class Node {
        Item item;
        Node next;
    }

    private Node first, last;

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
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

    public Item pop() {
        Item item = first.item;
        first  = first.next;
        if (isEmpty()) last = null;

        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public Item next() {
            Item item  = current.item;
            current = current.next;

            return item;
        }
    }
}
