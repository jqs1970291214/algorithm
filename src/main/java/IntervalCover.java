import java.util.*;

/**
 * @Descriptions 贪心算法：区间覆盖问题
 * @Company
 * @Author Junqson
 * @Date 2019/3/4 14:39
 * @Version 1.0
 *
（1）区间完全覆盖问题
问题描述：给定一个长度为m的区间，再给出n条线段的起点和终点（注意这里是闭区间），求最少使用多少条线段可以将整个区间完全覆盖
样例：
区间长度8，可选的覆盖线段[2,6],[1,4],[3,6],[3,7],[6,8],[2,4],[3,5]
算法过程：
1、将每一个区间按照左端点递增顺序排列，拍完序后为[1,4]，[2,4]，[3,5]，[2,6]，[3,6]，[3,7]，[6,8]
2、设置一个变量表示已经覆盖到的区域。再剩下的线段中找出所有左端点小于等于当前已经覆盖到的区域的右端点的线段中，右端点最大的线段在加入，直到已经覆盖全部的区域
3、ex:
假设第一步加入[1,4]，那么下一步能够选择的有[2,6]，[3,5]，[3,6]，[3,7]，由于7最大，所以下一步选择[3,7]，最后一步只能选择[6,8]，这个时候刚好达到了8退出，所选区间为3

（2）最大不相交覆盖
问题描述: 给定一个长度为m的区间，再给出n条线段的起点和终点（开区间和闭区间处理的方法是不同，这里以开区间为例），问题是从中选取尽量多的线段，使得每个线段都是独立的，就是不和其它有任何线段有相交的地方
样例：
区间长度8，可选的覆盖线段[2,6],[1,4],[3,6],[3,7],[6,8],[2,4],[3,5]
算法过程:
对线段的右端点进行升序排序，每加入一个线段，然后选择后面若干个（也有可能是一个）右端点相同的线段，选择左端点最大的那一条，如果加入以后不会跟之前的线段产生公共部分，那么就加入，否则就继续判断后面的线段

1、排序: 将每一个区间按右端点进行递增顺序排列，拍完序后为[2,4]，[1,4]，[3,5]，[3,6]，[2,6]，[3,7]，[6,8]
2、第一步选取[2,4]，发现后面只能加入[6,8],所以区间的个数为2
3、贪心证明: 因为需要尽量多的独立的线段，所以每个线段都尽可能的小，对于同一右端点，左端点越大，线段长度越小。那么为什么要对右端点进行排序呢？如果左端点进行排序，那么右端点是多少并不知道，那么每一条线段都不能对之前所有的线段进行一个总结，那么这就明显不满足贪心的最有字结构了。

（3）区间选点问题
区间选点问题。数轴上有n个闭区间[ai,bi]。取尽量少的点，使得每个区间内都至少有一个点（不同区间内含的点可以是同一个）。
贪心思想：先按b从小到大进行排序，再选择b0作为选点pos，如果出现ai>pos，则以bi作为pos，再按照这样的方式迭代。直至所有区间遍历完。

// rightR为区间右端点位置bi
for(int i = 1; i < n; i++) {
    // 如果下一个区间的左端点在rightR后面，更新rightR为下个区间的右端点
    if(r[i].left > rightR) {
        rightR = r[i].right;
    }
    pos.push_back(rightR);
}



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
