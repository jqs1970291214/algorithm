package bytedance;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/4/14 10:04
 * @Version 1.0
 */
public class Solution1 {
    public static int[][] move = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] map = new int[11][11];
        int curline = 1; int column = 0;
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] linearr = line.split(" ");
            for (int i = 0; i < linearr.length; i++) {
                map[curline][i + 1] = Integer.valueOf(linearr[i]);
            }
            curline++;
            column = linearr.length;
        }

        int s = 0;
        boolean noOne = true;
        while(true) {
            noOne = true;
            int[][] twopos = new int[200][2];
            int pos = 0;
            for (int i = 1; i < curline; i++) {
                for (int j = 1; j <= column; j++) {
                    if (map[i][j] == 1) {
                        noOne = false;
                        boolean nosolove = true;
                        for (int k = 0; k < 4; k++) {
                            if (map[i + move[k][0]][j + move[k][1]] != 0) {
                                nosolove = false;
                                break;
                            }
                        }
                        if (nosolove) {
                            System.out.println(-1);
                            return;
                        }
                    } else if (map[i][j] == 2) {
                        twopos[pos][0] = i;
                        twopos[pos][1] = j;
                        pos++;
                    }
                }
            }
            for (int p = 0; p < pos; p++) {
                for (int k = 0; k < 4; k++) {
                    int x = twopos[p][0] + move[k][0];
                    int y = twopos[p][1] + move[k][1];
                    if (x > 0 && y > 0 && x < curline && y <= column && map[x][y] == 1) {
                        map[x][y] = 2;
                    }
                }
            }
            if (noOne) break;

            s++;
        }

        System.out.println(s);


    }
}
