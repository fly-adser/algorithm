package SortAlgorithm;

public class Item implements Comparable<Item> {

    private int value;

    public Item(int param) {
        value = param;
    }

    @Override
    public int compareTo(Item other) {
        if (this.value < other.value) {
            return -1;
        } else if (this.value == other.value) {
            return 0;
        } else {
            return 1;
        }
    }

    public int getValue() {
        return value;
    }
}
