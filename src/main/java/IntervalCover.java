import java.util.*;

/**
 * @Descriptions 贪心算法：区间覆盖相关问题，最大不相交区间个数（安排最多的活动）
 * @Company
 * @Author Junqson
 * @Date 2019/3/4 14:39
 * @Version 1.0
 */
public class IntervalCover {

    static class Interval implements Comparable {
        int start;
        int end;
        @Override
        public int compareTo(Object oo) {
            // end 小的排在前面，end相同的，start小的排在前面
            Interval o = (Interval) oo;
            if (this.end < o.end) {
                return -1;
            } else if (this.end == o.end && this.start < o.start) {
                return -1;
            }
            return 1;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + String.valueOf(start) + ", " + String.valueOf(end) + "]";
        }
    }


    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        // 给定总区间长度
        int start = 1;
        int end = 10;
        // 待选区间
        int[][] subintervals = new int[][]{{1, 5}, {2, 4}, {3, 5}, {5, 9}, {6, 8}, {8, 10}};
        // 初始化
        Arrays.asList(subintervals)
                .stream()
                .forEach(is -> intervals.add(new Interval(is[0], is[1])));

        Collections.sort(intervals);

        //intervals.forEach(System.out::println);
        // 已覆盖末尾
        int[] ee = {-1};
        // 已选择区间个数
        int[] count = {0};
        intervals.stream().forEach(it -> {
            // 防止和前面的重叠
            if (it.start >= ee[0]) {
                count[0]++;
                ee[0] = it.end;
                System.out.println(it);
            }
        });

    }





}
