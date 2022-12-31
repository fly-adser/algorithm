package StackAndQueue;

/**
 * 使用列表实现队列
 */
public class QueueByListV1 {
    private Node first, last;

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
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

    public String pop() {
        String item = first.item;
        first       = first.next;
        if (isEmpty()) last = null;

        return item;
    }
}
