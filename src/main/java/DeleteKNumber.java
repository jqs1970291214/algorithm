/**
 * 给定n位数，删除k位，使结果最小，以下为贪心实现
 * 贪心，dp
 *
 * @author Junqson
 * @date 2018/11/11 14:55
 */
public class DeleteKNumber {

    public static int deleteK(int n, int k) {
        int deleted = 0;
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        if (k > sb.length()) return -1;
        if (k == sb.length()) return 0;
        while (deleted < k) {
            int i = 0;
            boolean haveDel = false;
            for (; i < sb.length() - 1; i++) {
                int front = sb.charAt(i);
                int next = sb.charAt(i + 1);
                if (front > next) {
                    sb = sb.deleteCharAt(i);
                    haveDel = true;
                    break;
                }
            }
            // 如果不存在逆序对，删除最后一位
            if (!haveDel) sb.deleteCharAt(sb.length() - 1);
            deleted++;
        }
        return Integer.valueOf(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(deleteK(1521342, 3));
        System.out.println(deleteK(1132, 1));
    }
}
