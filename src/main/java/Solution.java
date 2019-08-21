
import java.util.Scanner;

public class Solution {

    public static int minn = Integer.MAX_VALUE;

    public static void calc(int curm, int m, int cn, int cur, int limit, int[] chips) {
        if (cur > limit || curm > m) {
            return;
        } else if (cur == limit && curm == m) {
            if (cn < minn) minn = cn;
            return;
        }
        for (int i = chips.length - 1; i >= 0; i--) {
            calc(curm + 1, m,cn + 1, cur + chips[i], limit, chips);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[] chips = new int[n];
        for (int i = 0; i < n; i++) {
            chips[i] = in.nextInt();
        }
        calc(0,m,0, 0, m, chips);
        System.out.println(minn);

    }
}

class Solution2 {

    public static void calc(int minmon, int mon, int curwuli, int maxx) {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] wuli = new int[n];
        int maxx = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            wuli[i] = in.nextInt();
            if (wuli[i] > maxx) {
                maxx = wuli[i];
            }
        }

    }
}


class Temp<T> {
    T data;
    public T getData() {
        return data;
    }

    public <P> P getP(P p) {
        System.out.println(p.toString());
        return p;
    }

    public static void main(String[] args) {
        System.out.println(new Temp().getP(new Object()));
        System.out.println(new Temp().getP("12123"));
        String s = new Temp<String>().getP("12");

    }
}


