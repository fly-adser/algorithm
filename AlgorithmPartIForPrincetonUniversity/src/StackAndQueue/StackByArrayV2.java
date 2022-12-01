package StackAndQueue;

import java.util.Iterator;

/**
 * 1.使用数组实现栈；2.通过泛型让栈支持不同的数据类型；3.通过iterator接口实现迭代器
 */
public class StackByArrayV2<Item> implements Iterable<Item>{
    private Item[] s;
    private int N = 0;

    public StackByArrayV2() {
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public Item pop() {
        Item item = s[--N];
        s[N]   = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);

        return item;
    }

    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }

        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return s[--i];
        }
    }
}
