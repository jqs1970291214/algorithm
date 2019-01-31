import java.util.HashMap;
import java.util.Map;

/**
 * @Descriptions 利用hash表和链表实现LRU模型
 * @Company
 * @Author Junqson
 * @Date 2019/1/30 19:57
 * @Version 1.0
 */
public class LRU {

    private static class Node {
        public Object data;
        public Node prev;
        public Node next;
        public long timestamp;

        public Node() {
        }

        public Node(Object data, Node prev, Node next, long timestamp) {
            this.data = data;
            this.prev = prev;
            this.next = next;
            this.timestamp = timestamp;
        }
    }

    /**
     * 固定容量
     */
    private static final int CAPACITY = 10;

    /**
     * 首尾结点
     */
    private static Node head = null;

    private static final Map<String, Node> dataMap = new HashMap<>();
    /**
     * 已有元素个数
     */
    private static int size = 0;
    /**
     * 过期时间 second
     */
    private static int expire = 2000;


    public static void setExpire(int expire) {
        LRU.expire = expire;
    }


    public static void put(String key, Object data) {
        // 新节点
        Node newNode = new Node(data,null, null, System.currentTimeMillis());
        if (size >= CAPACITY) {
            // 删除尾节点
            if (size == 1) {
                head = null;
            } else {
                head.prev.prev.next = head;
                head.prev = head.prev.prev;
            }
            size--;
        }
        if (head != null) {
            newNode.prev = head.prev;
            head.prev.next = newNode;
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else {
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        }
        dataMap.put(key, newNode);
        size++;
    }


    public static Object get(String key) {
        Node dataNode = null;
        if (dataMap.containsKey(key) && (dataNode = dataMap.get(key)) != null ) {
            // 检查是否过期
            long current = System.currentTimeMillis();
            if (current - dataNode.timestamp < expire) {
                dataNode.timestamp = current;
                // 将dataNode从原来的位置删除
                dataNode.next.prev = dataNode.prev;
                dataNode.prev.next = dataNode.next;
                // 插入头部
                dataNode.next = head;
                dataNode.prev = head.prev;
                head.prev.next = dataNode;
                head.prev = dataNode;
                head = dataNode;
                return dataNode.data;
            }
        }
        return null;
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(LRU.get("sad"));
        LRU.put("1", 10);
        System.out.println(LRU.get("1"));
        LRU.setExpire(3);
        Thread.sleep(1);
        System.out.println(LRU.get("1"));
        Thread.sleep(2);
        System.out.println(LRU.get("1"));
        LRU.setExpire(100);
        System.out.println(LRU.get("1"));

    }





}
