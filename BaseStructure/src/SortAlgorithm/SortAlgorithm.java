package SortAlgorithm;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 利用各类排序算法对抽象数据类型的数组进行排序，如果希望让其适应不同的数据类型，修改底层数据类型Item即可
 */
public class SortAlgorithm {

    /**
     * 选择排序
     * @param items，待排序数组
     */
    public void selectSort(Item[] items) {
        int len = items.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (isLess(items[j], items[minIndex])) {
                    minIndex = j;
                }
            }

            swap(items, i, minIndex);
        }
    }

    /**
     * 插入排序
     * @param items，待排序数组
     */
    public void insertSort(Item[] items) {
        int len = items.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (isLess(items[j], items[j-1])) {
                    swap(items, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序
     * @param items，待排序数组
     */
    public void shellSort(Item[] items) {
        int len = items.length;
        int gap = 1;
        while (gap < len / 3) gap = 3 * gap + 1;

        while (gap >= 1) {
            for (int i = gap; i < len; i++) {
                for (int j = i; j >= gap && isLess(items[j], items[j-gap]); j -= gap) {
                    swap(items, j, j - gap);
                }
            }

            gap = gap / 3;
        }
    }

    /**
     * 归并排序
     * @param items，待排序数组
     */
    public void mergeSort(Item[] items) {
        Item[] helper = new Item[items.length];
        sort(items, helper, 0, items.length-1);
    }

    /**
     * 归并排序辅助函数：
     * @param items，待排序数组
     * @param helper，辅助数组
     * @param low，左边界
     * @param high，右边界
     */
    public void sort(Item[] items, Item[] helper, int low, int high) {
        if (low >= high) return;

        int mid = low + (high - low) / 2;
        sort(items, helper, low, mid);
        sort(items, helper, mid+1, high);
        merge(items, helper, low, mid, high);
    }

    /**
     * 归并排序辅助函数：两个子数组归并过程
     * @param items，待排序数组
     * @param helper，辅助数组
     * @param low，左边界
     * @param mid，中间位置指针
     * @param high，右边界
     */
    public void merge(Item[] items, Item[] helper, int low, int mid, int high) {

        for (int k = low; k <= high; k++) {
            helper[k] = items[k];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (j > high) {
                items[k] = helper[i++];
            } else if (i > mid) {
                items[k] = helper[j++];
            } else if (isLess(helper[j], helper[i])) {
                items[k] = helper[j++];
            } else {
                items[k] = helper[i++];
            }
        }
    }

    /**
     * 快速排序
     * @param items，待排序数组
     */
    public void quickSort(Item[] items) {
        StdRandom.shuffle(items);
        sort(items, 0, items.length-1);
    }

    /**
     * 快速排序辅助数组：通过递归不断对子数组排序
     * @param items，待排序数组
     * @param low，指标左边界
     * @param high，指标右边界
     */
    public void sort(Item[] items, int low, int high) {
        if (low >= high) return;
        int index = partition(items, low, high);
        sort(items, low, index - 1);
        sort(items, index + 1, high);
    }

    /**
     * 快速排序辅助数组：对数组进行划分，对于标的元素，左边的值都小于它，右边的值都大于它
     * @param items，待排序数组
     * @param low，指针左边界
     * @param high，指针右边界
     * @return，已排序元素的指针
     */
    public int partition(Item[] items, int low, int high) {
        int i = low, j = high + 1;
        while (true) {
            while (isLess(items[++i], items[low]))
                if (i == high) break;
            while (isLess(items[low], items[--j]))
                if (j == low) break;

            if (i >= j) break;
            swap(items, i, j);
        }

        swap(items, low, j);
        return j;
    }

    public boolean isLess(Item itemA, Item itemB) {
        return itemA.compareTo(itemB) < 0;
    }

    public void swap(Item[] items, int indexA, int indexB) {
        Item item     = items[indexA];
        items[indexA] = items[indexB];
        items[indexB] = item;
    }
}

