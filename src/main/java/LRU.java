
import java.util.*;
import java.util.Map;


/**
 * @Descriptions 利用hash表和双向链表实现LRU模型(线程不安全)
 * @Company
 * @Author Junqson
 * @Date 2019/1/30 19:57
 * @Version 1.0
 */
public class LRU<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node pre;
        Node next;

        public Node() { }

        public Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private int CACHE_SIZE;
    private Map<K, Node<K, V>> map;
    private Node first;
    private Node last;


    public LRU(int cacheSize) {
        this.CACHE_SIZE = cacheSize;
        this.map = new HashMap<>();
    }


    private Node<K, V> getFromMap(K k) {
        return map.get(k);
    }


    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }


    private void moveToFirst(Node<K, V> node) {
        if (first == node) {
            return;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node == last) {
            last = last.pre;
        }
        if (first == null || last == null) {
            first = last = node;
            return;
        }

        node.next = first;
        first.pre = node;
        first = node;
        node.pre = null;
    }

    public V get(K k) {
        Node<K, V> node = map.get(k);
        if (node == null) {
            return null;
        } else {
            moveToFirst(node);
            return node.value;
        }

    }

    public void remove(K k) {
        Node node = getFromMap(k);
        if (node != null) {
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node == first) {
                first = node.next;
            }
            if (node == last) {
                last = node.pre;
            }
        }
        map.remove(k);
    }


    public void put(K k, V v) {
        Node<K, V> node;
        if ((node = getFromMap(k)) == null) {
            if (map.size() + 1 > CACHE_SIZE) {
                removeLast();
                map.remove(last.key);
            }
            node = new Node<>();
            node.key = k;
        }
        node.value = v;
        map.put(k, node);
        moveToFirst(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<K, V> node = first;
        while (node != null) {
            sb.append("{");
            sb.append(node.key);
            sb.append(", ");
            sb.append(node.value);
            sb.append("}, ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LRU<Integer, String> lru = new LRU<>(3);
        lru.put(1, "1");
        lru.put(2, "2");
        // lru.get(1);
        lru.put(1, "2");
        lru.put(3, "3");
        lru.put(4, "3");

        System.out.println(lru);
    }



}





/**
 * 继承LinkedHashSet来实现LRU缓存，也可以使用委托
 * @param <K>
 * @param <V>
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        // 开启accessOrder 按照顺序访问
        super((int)Math.ceil(cacheSize / 0.75), 0.75f, true);
        CACHE_SIZE = cacheSize;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        /**
         * 重写是否删除老数据的条件
         */
        return size() > CACHE_SIZE;
    }


    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(5);
        for (int i = 1; i <= 10; i++) {
            lruCache.put(i, String.valueOf(i));
        }
        String first = lruCache.get(8);
        System.out.println(first);
        lruCache.get(9);
        for (int i = 11; i < 13; i++) {
            lruCache.put(i, String.valueOf(i));
        }

        System.out.println(lruCache);
        LinkedList ll = new LinkedList();



    }

}
