//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class Main {
//
//
//    private static long ans = 0;
//
//    private static int[] extra;
//
//    public static void mergeSort(int[] arr, int l, int r) {
//        int mid = (l + r) / 2;
//        if (l < r) {
//            mergeSort(arr, l, mid);
//            mergeSort(arr, mid + 1, r);
//        } else {
//            return;
//        }
//
//        int ll = l, rr = mid + 1, p = l;
//
//        while (ll <= mid && rr <= r) {
//            if (arr[ll] <= arr[rr]) {
//                extra[p] = arr[ll++];
//            } else {
//
//                for (int i = ll; i <= mid; i++) {
//                    System.out.print("(" + String.valueOf(arr[i]) + "," + String.valueOf(arr[rr]) + ")");
//                    System.out.print("(" + String.valueOf(i) + "," + String.valueOf(rr) + ")");
//
//                    System.out.print(" " + String.valueOf(rr - i));
//                    System.out.println();
//                }
//                extra[p] = arr[rr++];
//            }
//            p++;
//        }
//
//        while (ll <= mid) {
//            extra[p++] = arr[ll++];
//        }
//
//        while (rr <= r) {
//            extra[p++] = arr[rr++];
//        }
//
//
//        for (int i = l; i <= r; i++) {
//            arr[i] = extra[i];
//        }
//
//
//    }
//
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] arr = new int[n];
//
//        int[] seq = new int[n];
//
//        for (int i = 0; i < seq; i++) {
//            seq[i] = i;
//        }
//
//        extra = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = sc.nextInt();
//        }
//
//        mergeSort(arr, 0, arr.length - 1);
//        System.out.println(ans);
//
//        //Arrays.stream(arr).forEach(System.out::println);
//    }
//
//
//}