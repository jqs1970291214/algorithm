import java.io.ObjectInputStream;
import java.lang.reflect.Array;

/**
 * summary
 * descriptions
 *
 * @author Junqson
 * @date 2018/11/8 19:43
 */
public class Sort {


    // 冒泡排序 O(n^2) 大元素向右侧的有序区进行冒泡
    public static void BubbleSort(int[] arr, int n) {
        int i, j, k;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    k = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = k;
                }
            }
        }

    }

    // 插入排序
    public static void InsertSort(int[] arr, int n) {
        int i, j, t;
        for (i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                t = arr[i];
                for (j = i; j > 0  && arr[j - 1] > t; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[j] = t;
            }
        }
    }



    // 选择排序 选择最小的依次放在前面有序区的最后 O(n^2)
    public static void SelectSort(int[] arr, int n) {
        int i, j, k, min;
        for (i = 0; i < arr.length - 1; i++) {
            min = i;
            for (j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                k = arr[min];
                arr[min] = arr[i];
                arr[i] = k;
            }
        }
    }

    // 快速排序算法
    private static void qsort(int[] arr, int left, int right) {
        if (left >= right) return;
        int key = arr[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && arr[r] >= key) {
                r--;
            }
            while (l < r && arr[l] <= key) {
                l++;
            }
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
        }
        arr[left] = arr[l];
        arr[l] = key;
        qsort(arr, left, l);
        qsort(arr, l + 1, right);
    }

    // 快排入口
    public static int[] quicksort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
        return arr;
    }


    //希尔排序
    public static void shellSort(int[] arr, int start, int end) {
        int increament = end - start + 1;
        int temp = 0;
        do {
            increament = increament / 3 + 1;
            for (int i = start + increament; i < end; i++) {
                if (arr[i - increament] > arr[i]) {
                    temp = arr[i];
                    int j = i - increament;
                    do {
                        arr[j + increament] = arr[j];
                        j -= increament;
                    } while(j >= start && arr[j] > temp);
                    arr[j + increament] = temp;
                }
            }
        } while (increament > 1);
    }


    // 归并排序
    public static void MergeSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        } else {
            int mid = (start + end) / 2;
            MergeSort(arr, start, mid);
            MergeSort(arr, mid + 1, end);
            int[] temp = new int[end - start + 1];
            int p = 0;
            int i = start;
            int j = mid + 1;
            while(i <= mid) {
                if (j <= end) {
                    if (arr[i] > arr[j]) {
                        temp[p++] = arr[j++];
                    } else {
                        temp[p++] = arr[i++];
                    }
                } else {
                    // 将i中剩下的拷过去
                    System.arraycopy(arr, i, temp, p, mid - i + 1);
                    break;
                }
            }
            if (j <= end) {
                // 将j中剩下的拷过去
                System.arraycopy(arr, j, temp, p, end - j + 1);
            }
            // 将temp拷回
            System.arraycopy(temp, 0, arr, start, temp.length);
        }
    }



    public static void main(String[] args) {
        int[] arr = {10, 7, 3, 6, 2, 5};
        // BubbleSort(arr, arr.length);
        // InsertSort(arr, arr.length);
        // SelectSort(arr, arr.length);

//        int[] sorted = quicksort(arr);
//        System.out.println(sorted);
//        shellSort(arr, 0, arr.length);
        MergeSort(arr, 0, arr.length - 1);
        System.out.println(arr);
        System.out.println();
    }
}