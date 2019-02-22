import java.util.ArrayList;

/**
 * @Descriptions 顺时针打印矩阵
 * @Company
 * @Author Junqson
 * @Date 2019/2/21 15:55
 * @Version 1.0
 */
public class PrintMatrix {
    public static void print(int[][] data) {
        ArrayList<Integer> printList = new ArrayList<>();
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int x, y, nx, ny;
        x = y = 0;
        while (true) {
            printList.add(data[x][y]);
            // System.out.println(data[x][y]);
            data[x][y] = Integer.MAX_VALUE;
            nx = x + direction[d][0];
            ny = y + direction[d][1];
            if (nx > -1 && ny > -1 && nx < data.length && ny < data[0].length && data[nx][ny] != Integer.MAX_VALUE) {
                x = nx;
                y = ny;
            } else {
                d++;
                if (d > 3) {
                    d %= 4;
                }
                nx = x + direction[d][0];
                ny = y + direction[d][1];
                if (nx > -1 && ny > -1 && nx < data.length && ny < data[0].length && data[nx][ny] != Integer.MAX_VALUE) {
                    x = nx;
                    y = ny;
                    continue;
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[][] data = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}};
        print(data);
    }
}
