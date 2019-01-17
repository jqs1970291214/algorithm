/**
 * @Descriptions 找出第k小的元素
 * @Company
 * @Author Junqson
 * @Date 2019/1/17 11:01
 * @Version 1.0
 */
public class FindKth {

    /**
        如果每次都要查找最小的元素放在前面，那么时间复杂度为O(kn)，当k是n的函数时，退化为O(n^2)
        此种方法可在O(n)时间找到

        先找到pivot，据此将区间分为两部分，如果privot的位置恰好是k的话，直接返回
        否则如果pivot > k，说明第k大的数在pivot左边，反之在右边，继续划分即可。
        如果区间只有一个了，说明找到了。

     */

    public static int find(int[] arr, int s, int e, int k) {
        if (s == e && s + 1 == k) {
            return arr[s];
        }
        int pivot = arr[e];
        int i = s;
        for (int j = s; j <e; j++) {
            if (arr[j] < pivot) {
                int temp = arr[i];
                arr[i++] = arr[j];
                arr[j] = temp;
            }
        }
        arr[e] = arr[i];
        arr[i] = pivot;
        if (i + 1 == k) {
            return arr[i];
        } else if (i + 1 > k) {
            return find(arr, s, i - 1, k);
        } else {
            return find(arr, i + 1, e, k);
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 7, 3, 4, 7, 7, 7, 7};
        System.out.println(find(arr, 0, arr.length - 1, 5));


    }
}
