
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

/**
 * @Descriptions 利用hash表和双向链表实现LRU模型
 * @Company
 * @Author Junqson
 * @Date 2019/1/30 19:57
 * @Version 1.0
 */
public class LRU {


    private LinkedList<String> cachedList = new LinkedList<>();
    private Map<String, String> dataMap = new HashMap<>();
    public String getDataFromDB() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    private int limit = 20;

    public String get(String key) {
        if (dataMap.containsKey(key)) {
            cachedList.remove(key);
            cachedList.addLast(key);
        } else {
            if (cachedList.size() >= limit) {
                String outOfDate = cachedList.pollFirst();
                dataMap.remove(outOfDate);
            }
            String data = getDataFromDB();
            dataMap.put(key, data);
            cachedList.addLast(key);
        }
        return dataMap.get(key);
    }

    public LRU(int limit) {
        if (limit > 0) {
            this.limit = limit;
        }
    }

    public static void main(String[] args) {

        LRU lru = new LRU(5);
        for (int i = 1; i <= 10; i++) {
            lru.get(String.valueOf(i % 7));
        }

        lru.get("3");
        lru.get("9");
        lru.get("3");
        lru.get("1");
        lru.get("2");
        for (String key : lru.cachedList) {
            System.out.println(key + "  " + lru.dataMap.get(key));
        }



    }




}
