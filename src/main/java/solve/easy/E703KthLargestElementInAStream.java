package solve.easy;

import java.util.PriorityQueue;

/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，
 * 不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，
 * 它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 */
public class E703KthLargestElementInAStream {

    public static void main(String[] args) {
        int k = 3;

        int[] arr = new int[]{4, 5, 8, 2};

        KthLargest kthLargest = new KthLargest(k, arr);

        // returns 4
        System.out.println(kthLargest.add(3));
        // returns 5
        System.out.println(kthLargest.add(5));
        // returns 5
        System.out.println(kthLargest.add(10));
        // returns 8
        System.out.println(kthLargest.add(9));
        // returns 8
        System.out.println(kthLargest.add(4));
    }
}

/**
 * Java 内置优先队列 相当于小顶堆
 */
class KthLargest {

    private PriorityQueue<Integer> queue;
    private int topN;


    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>(k);
        topN = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // 如果当前数组 元素小于3 直接入堆
        if (queue.size() < topN) {
            queue.add(val);
        } else if (queue.peek() < val) {
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }

}