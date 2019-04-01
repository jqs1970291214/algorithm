import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Descriptions 二叉查找树：建立，查找，插入，删除
 * @Author Junqson
 */
public class BinarySearchTree {
    private static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    private Node root;


    /**
     * 查找值等于data的节点
     * @param data
     * @return
     */
    public Node find(int data) {
        Node cur = root;
        while (cur != null) {
            if (cur.data == data) {
                return cur;
            } else if (cur.data > data){
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }


    /**
     * 插入data，如果存在重复，不进行插入
     * @param data
     */
    public void insert(int data) {
        Node  cur = root, node = new Node(data);
        while (cur != null) {
            if (cur.data == data) {
                return;
            } else if (cur.data > data) {
                if (cur.left == null) {
                    cur.left = node;
                    return;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = node;
                    return;
                }
                cur = cur.right;
            }
        }
        root = node;
    }


    /**
     * 删除某个节点
     */

    public Node remove(int data) {
        Node cur = root, fa = null;
        while (cur != null) {
            if (cur.data == data) {
                Node newSon = null;
                if (cur.left == null || cur.right == null) {
                    newSon = cur.left;
                    if (cur.left == null) newSon = cur.right;
                } else {
                    Node faa = cur;
                    newSon = cur.left;
                    while (newSon.left != null) {
                        faa = faa.left;
                        newSon = newSon.left;
                    }
                    faa.left = null;
                    newSon.left = cur.left;
                    newSon.right = cur.right;
                }
                if (fa != null) {
                    if (fa.left == cur) fa.left = newSon;
                    else fa.right = newSon;
                } else root = newSon;
                cur.left = cur.right = null;
                return cur;
            } else if (cur.data > data) {
                fa = cur;
                cur = cur.left;
            } else {
                fa = cur;
                cur = cur.right;
            }
        }
        return null;
    }

    /**
     * 打印有序数组
     */

    public List<Node> inOrderArray() {
        if (root == null) {
            return Collections.emptyList();
        }
        ArrayList<Node> list = new ArrayList<>();
        inOrder(list, root);
        return list;
    }

    private void inOrder(List list, Node node) {
        assert node != null;
        if (node.left != null) {
            inOrder(list, node.left);
        }
        list.add(node);
        if (node.right != null) {
            inOrder(list, node.right);
        }
    }

    /**
     * 查找最大值
     */
    public Node getMax() {
        Node cur = root;
        while (cur != null) {
            if (cur.right != null) {
                cur = cur.right;
            } else {
                break;
            }
        }
        return cur;
    }

    /**
     * 查找最小值
     */
    public Node getMin() {
        Node cur = root;
        while (cur != null) {
            if (cur.left != null) {
                cur = cur.left;
            } else {
                break;
            }
        }
        return cur;
    }

    /**
     * 区间查找
     */
    public ArrayList<Node> intervalSearch(int lower, int upper) {
        return null;
    }


    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(5);
        bst.insert(3);
        bst.insert(8);
        bst.insert(7);
        bst.insert(9);

        bst.remove(8);


        System.out.println(bst.inOrderArray().toString());
    }
}

