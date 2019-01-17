import com.sun.scenario.effect.Merge;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

    // 快速排序2
    public static void qsort2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = arr[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (arr[j] < pivot) {
                int temp = arr[i];
                arr[i++] = arr[j];
                arr[j] = temp;
            }
        }
        arr[r] = arr[i];
        arr[i] = pivot;
        // pivot不必参与
        qsort2(arr, l, i - 1);
        qsort2(arr, i + 1, r);
    }



    // 快排入口
    public static int[] quicksort(int[] arr) {
//        qsort(arr, 0, arr.length - 1);
        qsort2(arr, 0, arr.length - 1);

        return arr;
    }


    //希尔排序, increment迭代方法  increament = increament / 3 + 1
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
            // 下面可以通过哨兵简化代码，在数组尾部放上MAX值，当一个数组空了之后，代码依然适用
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


    public static void sortTime() {
        int[] arr = new int[100000];
        Random ra = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = ra.nextInt(100001);
        }

        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        int[] arr4 = Arrays.copyOf(arr, arr.length);
        int[] arr5 = Arrays.copyOf(arr, arr.length);


        // 冒泡排序比较插入排序，数据规模比较大时，插入排序优于冒泡排序，不用做频繁的交换操作


        long time = System.currentTimeMillis();
        BubbleSort(arr, arr.length);
        long time2 = System.currentTimeMillis();
        System.out.println("bsort : " + String.valueOf(time2 - time) + "ms");

        InsertSort(arr2, arr2.length);
        long time3 = System.currentTimeMillis();
        System.out.println("isort : " + String.valueOf(time3 - time2) + "ms");

        quicksort(arr3);
        long time4 = System.currentTimeMillis();
        System.out.println("qsort : " + String.valueOf(time4 - time3) + "ms");

        MergeSort(arr4, 0, arr.length - 1);
        long time5 = System.currentTimeMillis();
        System.out.println("msort : " + String.valueOf(time5 - time4) + "ms");

        heapSort(arr5, arr.length);
        long time6 = System.currentTimeMillis();
        System.out.println("hsort : " + String.valueOf(time6 - time5) + "ms");
    }


    /**
     * 堆排序，大顶堆。
     * 构建初始堆从最后一个有子节点往前调整
     */
    public static void heapSort(int[] arr, int len) {
        initHeap(arr, arr.length);
        for (int i = 1; i < arr.length; i++) {
            swap(arr, 0, len - i);
            adjust(arr, 0, len - i);
        }

    }



    // 初始化堆
    public static void initHeap(int[] arr, int len) {
        int lastHasSon = len / 2 - 1;
        for (int i = lastHasSon; i >= 0; i--) {
            adjust(arr, i, len);
        }
    }


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
        }else if (l >= r){
            swap(arr, i, i * 2 + 1);
            adjust(arr, i * 2 + 1, len);
        }else {
            swap(arr, i, i * 2 + 2);
            adjust(arr, i * 2 + 2, len);
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
//        MergeSort(arr, 0, arr.length - 1);
//        heapSort(arr, arr.length);
//        quicksort(arr);

        sortTime();


        System.out.println();
    }



}