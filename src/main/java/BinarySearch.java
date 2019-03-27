/**
 * @Descriptions 二分查找和几种常见的变体
 * @Company
 * @Author Junqson
 * @Date 2019/3/27 9:58
 * @Version 1.0
 */
public class BinarySearch {
    // 普通的二分查找，不允许出现重复的元素（不能保证取到哪一个）
    public static int normalBs(int[] a, int n) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (a[mid] > n) {
                r = mid - 1;
            } else if (a[mid] < n){
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 等于n中的第一个
    public static int firstequalBs(int[] a, int n) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (a[mid] >= n) {
                if (mid == 0 || a[mid - 1] != n) {
                    if (a[mid] == n) return mid;
                }
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
    // 最后一个等于n的
    public static int lastequalBs(int[] a, int n) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (a[mid] > n) {
                r = mid - 1;
            } else {
                if (mid == a.length - 1 || a[mid + 1] != n) {
                    if (a[mid] == n) return mid;
                }
                l = mid + 1;
            }
        }
        return -1;
    }

    // 第一个大于等于n的
    public static int firstGeBs(int[] a, int n) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (a[mid] >= n) {
                if (mid == 0 || a[mid - 1] < n) {
                    return mid;
                }
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }


    // 最后小于等于n的
    public static int lastLeBs(int[] a, int n) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (a[mid] > n) {
                r = mid - 1;
            } else {
                if (mid == a.length - 1 || a[mid + 1] > n) {
                    return mid;
                }
                l = mid + 1;
            }
        }
        return -1;
    }



    // 测试所有
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 4, 4, 8, 8};
        System.out.println(normalBs(a, 4));
        System.out.println(firstequalBs(a, 4));
        System.out.println(lastequalBs(a, 4));
        System.out.println(firstGeBs(a, 7));
        System.out.println(firstGeBs(a, 8));
        System.out.println(firstGeBs(a, 2));
        System.out.println(lastLeBs(a, 7));
        System.out.println(lastLeBs(a, 4));
        System.out.println(lastLeBs(a, 20));
    }

}
