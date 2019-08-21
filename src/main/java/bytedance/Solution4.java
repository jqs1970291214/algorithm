package bytedance;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/4/14 11:43
 * @Version 1.0
 */
public class Solution4 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> lives = new HashMap<Integer, Integer>();
        int maxsec = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int nn = in.nextInt();
            for (int j = 1; j <= nn; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (x > y) {
                    y = x + y;
                    x = y - x;
                    y = y - x;
                }
                int fac = x * 10 + y;
                lives.put(fac, lives.get(fac + 1));
            }
        }
    }
}

