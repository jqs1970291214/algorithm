package Test;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Descriptions 度小满笔试题
 * @Company
 * @Author Junqson
 * @Date 2019/4/3 10:49
 * @Version 1.0
 */
public class Main {
    static class Count {
        int count;
        int left;
        public Count() {
            this.count = 1;
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int[] num = new int[n];
        HashMap<Integer, Count> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = num[i] = in.nextInt();
            if (countMap.containsKey(a)) {
                countMap.get(a).count++;
            } else {
                countMap.put(a, new Count());
            }
        }
        int prev = 0;
        for (int i = 0; i < n - 1; i++) {
            Count c = countMap.get(num[i]);
            int right = n - i - 1;
            System.out.println(right);
            int diff = right - c.count + 1;
            System.out.println(diff);
            int ans = diff + prev - i + c.left;
            System.out.println("ans = " + Integer.valueOf(ans));
            prev = ans;
            c.left++;
            c.count--;
        }

    }

}
