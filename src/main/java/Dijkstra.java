
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descriptions Dijkstra 算法，使用邻接矩阵存储边
 * @Company
 * @Author Junqson
 * @Date 2019/2/22 20:11
 * @Version 1.0
 */
public class Dijkstra {

    // 源点
    private int source;

    // 节点
    private List<Integer> nodes;
    // 边集
    private int[][] lines;

    // 最短路，由于dijkstra算法只能计算单源最短路，这里假设从“0”出发
    private int[] distance;


    //初始化数据
    public Dijkstra initGraph() {

        nodes = Arrays.asList(0, 1, 2, 3, 4, 5);
        source = nodes.get(0);
        lines = new int[nodes.size()][nodes.size()];
        for (int i = 0; i < lines.length; i++) {
            Arrays.fill(lines[i], Integer.MAX_VALUE);
        }
        lines[0][1] = 20;
        lines[0][2] = 15;
        lines[1][0] = 2;
        lines[1][4] = 10;
        lines[1][5] = 30;
        lines[2][1] = 4;
        lines[2][5] = 10;
        lines[4][3] = 15;
        lines[5][3] = 4;
        lines[5][4] = 10;
        for (int i = 0; i < nodes.size(); i++) {
            lines[i][i] = 0;
        }
        distance = new int[nodes.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (lines[source][i] > 0 && lines[source][i] < Integer.MAX_VALUE) {
                distance[i] = lines[source][i];
            }
        }
        return this;
    }


    public void dijkstra() {
        HashSet<Integer> joined = new HashSet<>();
        joined.add(source);
        printDistance();
        for (int i = 1; i <= nodes.size(); i++) {
            int minn = Integer.MAX_VALUE;
            int selected = -1;
            for (int j = 0; j < distance.length; j++) {
                if (!joined.contains(j) && distance[j] < minn) {
                    minn = distance[j];
                    selected = j;
                }
            }
            joined.add(selected);
            for (int j = 0; j < nodes.size(); j++) {
                if (!joined.contains(j)) {
                    int dis = lines[selected][j] + distance[selected];
                    if (dis > 0 && dis < distance[j]) {
                        distance[j] = dis;
                    }
                }
            }
            printDistance();
        }
    }

    public void printDistance() {
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(distance[i] + " ");
        }
        System.out.println();
    }


    static ExecutorService executorService = Executors.newFixedThreadPool(5);


    public static void main(String[] args) {


        //new Dijkstra().initGraph().dijkstra();

    }

}
