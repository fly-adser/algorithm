package StackAndQueue;

/**
 * 使用数组实现栈，可以根据栈的大小实现自动对数组进行扩缩容
 */
public class StackByArrayV1 {
    private String[] s;
    private int N = 0;

    public StackByArrayV1() {
        s = new String[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N]        = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);

        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < s.length; i++) {
            copy[i] = s[i];
        }

        s = copy;
    }
}
