import java.util.Scanner;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/3/30 19:58
 * @Version 1.0
 */
public class Solution2 {

    public static int getBits(int n) {

        int ans = 0;
        while (n != 0) {
            int t = n % 2;
            if (t == 1) {
                ans++;
            }
            n = n / 2;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(getBits(n));
    }
}
