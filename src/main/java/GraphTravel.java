import java.util.*;

/**
 * @Descriptions 图的广度优先遍历（邻接表）
 * @Company
 * @Author Junqson
 * @Date 2019/2/12 17:01
 * @Version 1.0
 */
public class GraphTravel {
    private Map<String, List<String>> graph = new HashMap<String, List<String>>();

    public GraphTravel initGraphData() {
        graph.put("1", Arrays.asList("2", "3"));
        graph.put("2", Arrays.asList("1", "4", "5"));
        graph.put("3", Arrays.asList("1", "6", "7"));
        graph.put("4", Arrays.asList("2", "8"));
        graph.put("5", Arrays.asList("2", "8"));
        graph.put("6", Arrays.asList("3", "8", "9"));
        graph.put("7", Arrays.asList("3", "9"));
        graph.put("8", Arrays.asList("4", "5", "6"));
        graph.put("9", Arrays.asList("6", "7"));
        return this;
    }



    private Set<String> travelled = new HashSet<>();
    private LinkedList<String> queue = new LinkedList<>();

    /**
     * 遍历结点
     * @param node
     */
    public void travel(String node) {
        travelled.add(node);
        queue.addLast(node);
        System.out.println(node);
    }

    /**
     * 广度优先遍历
     */
    public void travelGraph(String start) {
        if (start != null && graph.containsKey(start)) {
            travel(start);
            while (!queue.isEmpty()) {
                graph.get(queue.pollFirst())
                        .stream()
                        .filter((adjoin) -> !travelled.contains(adjoin))
                        .forEach(this::travel);
            }
        }
    }

    public static void main(String[] args) {
        new GraphTravel().initGraphData().travelGraph("1");
    }


}
