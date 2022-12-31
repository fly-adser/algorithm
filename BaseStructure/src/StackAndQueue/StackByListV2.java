package StackAndQueue;

import java.util.Iterator;

/**
 * 1.使用List实现栈；2.通过泛型支持不同数据类型；3.通过iterator接口实现迭代器
 */
public class StackByListV2<Item> implements Iterable<Item>{

    private class Node {
        Item item;
        Node next;
    }

    public Node first = null;

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first         = new Node();
        first.item    = item;
        first.next    = oldFirst;
    }

    public Item pop() {
        Item item = first.item;
        first  = first.next;

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
            return current != null;
        }

        @Override
        public Item next() {
            Item item  = current.item;
            current = current.next;

            return item;
        }
    }
}
