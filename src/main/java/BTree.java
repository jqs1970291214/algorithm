import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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



    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isSubTree(TreeNode root1, TreeNode root2) {

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return isSubTree(root1.left, root2) || isSubTree(root1.right, root2);
        } else {
            LinkedList<TreeNode[]> nodeList = new LinkedList<>();
            TreeNode[] pair = new TreeNode[2];
            pair[0] = root1;
            pair[1] = root2;
            nodeList.offer(pair);
            while (!nodeList.isEmpty()) {
                TreeNode[] nodepair = nodeList.poll();
                TreeNode tr1 = nodepair[0];
                TreeNode tr2 = nodepair[1];
                if (tr1.left == null || tr2.left == null) {
                    if (tr1.left != tr2.left) {
                        return false;
                    }
                } else if (tr1.left.val != tr2.left.val){
                    return false;
                } else {
                    TreeNode[] pp = new TreeNode[2];
                    pp[0] = tr1.left;
                    pp[1] = tr2.left;
                    nodeList.offer(pp);
                }

                if (tr1.right == null || tr2.right == null) {
                    if (tr1.right != tr2.right) {
                        return false;
                    }
                } else if (tr1.right.val != tr2.right.val){
                    return false;
                } else {
                    TreeNode[] pp = new TreeNode[2];
                    pp[0] = tr1.right;
                    pp[1] = tr2.right;
                    nodeList.offer(pp);
                }

                return true;
            }
        }
        return true;

    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(2);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node6.left = node7;

        System.out.println(isSubTree(node1, node6));


    }




}
