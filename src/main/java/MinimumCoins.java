import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Descriptions 动态规划最少硬币找零问题
 * @Company
 * @Author Junqson
 * @Date 2019/9/7 21:52
 * @Version 1.0
 */
public class MinimumCoins {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        Arrays.sort(coins);

        int budget = sc.nextInt();
        int[] f = new int[budget + 1];
        f[0] = 0;

        for (int i = 1; i <= budget; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (coins[j] <= i) {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                } else {
                    break;
                }
            }
        }

        System.out.println(f[budget]);
    }

}
