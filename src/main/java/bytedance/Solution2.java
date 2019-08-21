package bytedance;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/4/14 10:54
 * @Version 1.0
 */
public class Solution2 {


    private static HashMap<Integer, String> haveGo = new HashMap<>();

    private static int minn = Integer.MAX_VALUE;

    private static int n = 0;

    static int[][] cost = new int[21][21];

    static boolean[] hasgo;


    static int start = 0;
    static String go = "go";


    public static void dfs(int curn, int curcost, int cur) {
        if (curcost > minn) return;
        if (curn == n) {
            if (curcost + cost[cur][start] < minn) {
                minn = curcost + cost[cur][start];
            }
            return;
        }
        for (int i = 1; i < n; i++) {
            if (!hasgo[i]) {
                hasgo[i] = true;
                dfs( curn + 1, curcost + cost[cur][i], i);
                hasgo[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        hasgo = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = in.nextInt();
            }
        }
        haveGo.put(start, go);
        dfs(1, 0, start);
        System.out.println(minn);

    }


}
