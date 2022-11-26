package StackAndQueue;

import java.util.Iterator;

/**
 * 1.使用数组实现栈；2.通过泛型让栈支持不同的数据类型；3.通过iterator接口实现迭代器
 */
public class StackByArrayV2<T> implements Iterable<T>{
    private T[] s;
    private int N = 0;

    public StackByArrayV2() {
        s = (T[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(T item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public T pop() {
        T item = s[--N];
        s[N]   = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);

        return item;
    }

    public void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }

        s = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class ArrayIterator implements Iterator<T> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return s[--i];
        }
    }
}
