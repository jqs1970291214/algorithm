import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Descriptions 判断是否是二叉搜索树
 * @Company
 * @Author Junqson
 * @Date 2019/3/30 20:04
 * @Version 1.0
 *
 *
 * 二叉搜索树的性质:
 *
 * - 空树是一棵二叉搜索树
 * - 当前节点大于左子树的所有节点，小于右子树的所有节点
 * - 左右子树都是二叉搜索树
 *
 */
public class Solution3 {

    static class Node {
        int v;
        Node left,right;
        public Node(int v) {
            this.v = v;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Node> nodelist = new LinkedList<>();
        String root = scanner.next();
        if (root.equals("None")) {
            System.out.println("True");
            return;
        }
        Node r = new Node(Integer.valueOf(root));
        nodelist.offer(r);
        while (scanner.hasNext()) {
            String left = scanner.next();
            if (!scanner.hasNext()) break;
            String right = scanner.next();
            if (left == null || left.equals("None") || right == null || right.equals("None")) {
                break;
            }
            int ln = Integer.valueOf(left);
            int rn = Integer.valueOf(right);
            Node fa = nodelist.poll();
            fa.left = new Node(ln);
            fa.right = new Node(rn);
            nodelist.offer(fa.left);
            nodelist.offer(fa.right);
        }


    }


}
