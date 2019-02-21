/**
 * @Descriptions 全排列
 * @Company
 * @Author Junqson
 * @Date 2019/2/21 12:39
 * @Version 1.0
 */
public class Permutation {


    public static void printPermutation(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; i++) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < k; i++) {
            int temp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = temp;

            printPermutation(data, n, k - 1);

            temp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = temp;
        }
    }


    public static void main(String[] args) {
        int[] data = {1, 2, 3};
        printPermutation(data, data.length, data.length);

    }
}
