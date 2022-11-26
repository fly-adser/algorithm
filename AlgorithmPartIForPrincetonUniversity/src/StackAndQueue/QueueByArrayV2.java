package StackAndQueue;

import java.util.Iterator;

/**
 * 1.使用数组实现队列；2.通过泛型让队列支持不同的数据类型；3.通过iterator接口实现迭代器
 */
public class QueueByArrayV2<T> implements Iterable<T>{
    private T[] s;
    private int head = 0;
    private int tail = 0;

    public QueueByArrayV2() {
        s = (T[]) new Object[1];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void push(T item) {
        if (tail == s.length) resize(2 * (tail - head));
        s[tail++] = item;
    }

    public T pop() {
        T item    = s[head];
        s[head++] = null;
        if ((tail - head) > 0 && (tail - head) == s.length / 4) resize(s.length / 2);

        return item;
    }

    public void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        for (int i = head; i < tail; i++) {
            copy[i-head] = s[i];
        }

        s = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class ArrayIterator implements Iterator<T> {

        private int current = head;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            T item   = s[current];
            current += 1;

            return item;
        }
    }
}
