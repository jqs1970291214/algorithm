/**
 * 计算两个字符串的编辑距离
 */
public class EditDistance {


    static int minDist = Integer.MAX_VALUE;

    public static void editDist(char[] str1, char[] str2, int i, int j, int m, int n, int editDist) {
        if (i == m || j == n) {
            if (i < m) editDist += (m - i);
            if (j < n) editDist += (n - j);
            if (editDist < minDist) minDist = editDist;
            return;
        }
        if (str1[i] == str2[j]) {
            editDist(str1, str2, i + 1, j + 1, m, n, editDist);
        } else {
            editDist(str1, str2, i, j + 1, m, n, editDist + 1);
            editDist(str1, str2, i + 1, j, m, n, editDist + 1);
            editDist(str1, str2, i + 1, j + 1, m, n, editDist + 1);
        }

    }


    public static int getMin(int x, int y, int z) {
        int minn = x < y ? x : y;
        minn = minn < z ? minn : z;
        return minn;
    }



    public static int dpEditDist(char[] str1, char[] str2, int m, int n) {
        int[][] mindist = new int[n][m];
        for (int i = 0; i < m; i++) {
            if (str1[i] == str2[0]) {
                mindist[0][i] = i;
            } else if (i != 0){
                mindist[0][i] = mindist[0][i - 1] + 1;
            } else {
                mindist[0][i] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (str1[0] == str2[i]) {
                mindist[i][0] = i;
            } else if (i != 0) {
                mindist[i][0] = mindist[i - 1][0] + 1;
            } else {
                mindist[i][0] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (str1[j] == str2[i]) {
                    mindist[i][j] = getMin(mindist[i - 1][j] + 1, mindist[i][j - 1] + 1, mindist[i - 1][j - 1]);
                } else {
                    mindist[i][j] = getMin(mindist[i - 1][j] + 1, mindist[i][j - 1] + 1, mindist[i - 1][j - 1] + 1);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%d ", mindist[i][j]);
            }
            System.out.println();
        }
        return mindist[n - 1][m - 1];

    }

    public static void main(String[] args) {
        char[] str1 = "mitcmu".toCharArray();
        char[] str2 = "mtacnu".toCharArray();
        // 回溯法
        minDist = Integer.MAX_VALUE;
        editDist(str1, str2, 0, 0, str1.length, str2.length, 0);
        System.out.println(minDist);
        // dp
        System.out.println(dpEditDist(str1, str2, str1.length, str2.length));
    }
}