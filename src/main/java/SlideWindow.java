import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Descriptions 滑动窗口最大值
 * @Company
 * @Author Junqson
 * @Date 2019/8/21 14:02
 * @Version 1.0
 */
public class SlideWindow {
    public static void main(String[] args) {

        // 滑动窗口最大值题解
        int[] arr = new int[] {2, 3, 4, 2, 6, 2, 5, 1};
        LinkedList<Integer> deqq = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        int size = 3;
        for (int i = 0; i < size; i++) {
            while (!deqq.isEmpty() && arr[deqq.peekLast()] < arr[i]) {
                deqq.pollLast();
            }
            deqq.offerLast(i);
        }
        for (int i = size - 1; i < arr.length; i++) {
            while (!deqq.isEmpty() && arr[deqq.peekLast()] < arr[i]) {
                deqq.pollLast();
            }
            deqq.offerLast(i);
            while (!deqq.isEmpty() && deqq.peekFirst() <= i - size) {
                deqq.pollFirst();
            }
            result.add(arr[deqq.peekFirst()]);

        }
        result.forEach(System.out::println);
    }
}
