package bytedance;

import java.util.Scanner;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/4/14 11:36
 * @Version 1.0
 */
public class Solution3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
        }
        double end = 0;
        for (int i = n - 1; i >= 0; i--) {
            end = (end + h[i]) / 2;
        }

        System.out.println((int) Math.ceil(end));

    }
}
