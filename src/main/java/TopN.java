import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Descriptions 查找给定数据集中的最大（小）的N个数据
 * @Company
 * @Author Junqson
 * @Date 2019/1/18 18:20
 * @Version 1.0
 */
@SuppressWarnings("Duplicates")
public class TopN {

    /**
     * 算法思想（以取最小的TopN为例）
     * 将数组的前N个做成大顶堆，顶上元素最大。
     * 遍历剩下的数组arr[n]...arr[length - 1]
     * 如果arr[i] >= 顶，pass。
     * 否则和顶交换，重新调整堆
     */


    public static int[] findLeastTopN(int[] arr, int n) {
        if (arr.length <= n) {
            return arr;
        }

        initHeap(arr, n);
        for (int i = n; i < arr.length; i++) {
            if (arr[i] < arr[0]) {
                swap(arr, 0, i);
                adjust(arr, 0, n);
            }
        }

        return Arrays.copyOf(arr, n);

    }

    // 初始化堆
    public static void initHeap(int[] arr, int len) {
        int lastHasSon = len / 2 - 1;
        for (int i = lastHasSon; i >= 0; i--) {
            adjust(arr, i, len);
        }
    }

    // 交换
    public static void swap(int arr[], int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

    // 调整
    public static void adjust(int[] arr, int i, int len) {
        int node = arr[i];
        int l, r;
        l = r = -1;
        if (i * 2 + 1 < len) {
            l = arr[i * 2 + 1];
        }
        if (i * 2 + 2 < len) {
            r = arr[i * 2 + 2];
        }

        if (node >= r && node >= l) {
            return;
        } else if (l >= r) {
            swap(arr, i, i * 2 + 1);
            adjust(arr, i * 2 + 1, len);
        } else {
            swap(arr, i, i * 2 + 2);
            adjust(arr, i * 2 + 2, len);
        }

    }

    /**
     * 这里我用Java中的PriorityQueue来实现一下取最大的前N个(小顶堆)
     * 优先级队列中，优先级数字越小越优先
     */

    public static int[] findLargestTopN(int[] arr, int n) {
        if (n >= arr.length) {
            return arr;
        }
        // 重新实现一下comparator，保证数字大的优先级高
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < arr.length; i++) {
            pq.offer(arr[i]);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = pq.poll();
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 7, 3, 5, 7, 3, 2, 1};
        int[] leastTopN = findLeastTopN(arr, 2);
        int[] largestTopN = findLargestTopN(arr, 3);

        System.out.println();
    }
}




