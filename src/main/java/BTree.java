/**
 * @Descriptions 二叉查找树
 * @Company
 * @Author Junqson
 * @Date 2019/2/12 17:25
 * @Version 1.0
 */
public class BTree {
    private static class Node {
        public int data;
        public Node left;
        public Node right;
    }
    private Node root;

    public void insert(Node node) {
        if (root == null) {
            root = node;
        } else {
        }
    }

    public void remove() {

    }

    public Node get(int order) {
        Node p = root;
        while (p != null) {
            if (order < p.data) {
                p = p.left;
            } else if (order > p.data) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }
}
