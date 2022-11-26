package StackAndQueue;

/**
 * 使用数组实现队列，可以根据队列大小自动实现数组的扩缩容
 */
public class QueueByArrayV1 {
    private String[] s;
    private int head = 0;
    private int tail = 0;

    public QueueByArrayV1() {
        s = new String[1];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void push(String item) {
        if (tail == s.length) resize(2 * (tail - head));
        s[tail++] = item;
    }

    public String pop() {
        String item = s[head];
        s[head++]   = null;
        if ((tail - head) > 0 && (tail - head) == s.length / 4) resize(s.length / 2);

        return item;
    }

    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = head; i < tail; i++) {
            copy[i - head] = s[i];
        }

        s = copy;
    }
}
