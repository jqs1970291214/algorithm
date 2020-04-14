import java.util.Scanner;

public class Solution1 {



    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        Len[] len = new Len[n];

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            len[i] = new Len();
            len[i].sum = arr[i];
            len[i].len = 1;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            if (len[i].sum + len[i - 1].sum <= len[i].sum) {
                len[i].sum += len[i - 1].sum;
                len[i].len++;
            }
            if (len[i].sum <= ans && len[i].len >= m) {
                ans = len[i].sum;
            }
        }

        if (ans ==  Integer.MAX_VALUE) {
            int total = 0;
            for (int i = 0; i < m; i++) {
                total += arr[m];
            }
            for (int i = m; i < n; i++) {
                total += arr[m];
                total -= arr[i - m];
                if (total < ans) {
                    ans = total;
                }
            }
        }




        System.out.println(ans);





    }


//    public static void main(String[] args) {
//
//    }

}


class Len {
    int sum;
    int len;
}
