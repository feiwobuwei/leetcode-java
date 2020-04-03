package solve.middle;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author minwei
 */
public class M215_KthLargestElementInAnArray {

    int[] nums = {3, 2, 1, 5, 6, 4};
    int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};

    @Test
    public void test() {
        int kthLargest = findKthLargest(nums, 2);
        System.out.println(kthLargest);
    }

    @Test
    public void test2() {
        int kthLargest = findKthLargest2(nums, 2);
        System.out.println(kthLargest);
    }

    @Test
    public void test3() {
        int kthLargest = findKthLargest3(nums2, 2);
        System.out.println(kthLargest);
    }

    /**
     * 排序 -- 93.89%
     * <p>
     * time O(nlgn)
     * space O()
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 最小优先队列 minimum heap priority queue -- 50.75%
     * <p>
     * 保持堆的大小为 k。每次超出堆大小时,最小元素出列。
     * 这样结束遍历时 堆中最小的元素就是原数组第K大的元素。
     * <p>
     * time O(Nlogk)
     * space O(k)
     */
    public int findKthLargest2(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 系统自带的优先队列类,入参为比较器。默认就是最小优先队列
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 等效写法

//        PriorityQueue<Integer> heap =
//                new PriorityQueue<>(Comparator.comparingInt(i -> i));

        // (取反值) 则为最大优先队列 这样最终结果就是2 相当于第k小的元素
//        PriorityQueue<Integer> heap =
//                new PriorityQueue<>(Comparator.comparingInt(i -> -i));

        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                // 根据最小优先队列 每次最小的元素出队
                heap.poll();
            }
        }

        return heap.poll();
    }

    /**
     * 快速排序 -- 分治法 84.74%
     * <p>
     * 平均情况 O(N)，最坏情况 O(N^2)
     * 空间复杂度 : O(1)
     */
    public int findKthLargest3(int[] nums, int k) {
        int n = nums.length;
        return findKthLargestRec(nums, 0, n - 1, k);
    }

    /**
     * 在arr数组的[left, right]范围内寻找第k大的元素
     *
     * @param arr   输入数组
     * @param left  左边界
     * @param right 右边界
     * @param k     第k大
     * @return 结果
     */
    private int findKthLargestRec(int[] arr, int left, int right, int k) {
        // 随机选择主元放在头部
        swap(arr, left, (int) (Math.random() * (right - left + 1)) + left);
        // 在[left + 1, j]中存储小于arr[left]的值
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] > arr[left]) {
                swap(arr, i, j + 1);
                j++;
            }
        }
        // 交换后 主元元素左边都大于自己 右边都小于等于自己
        swap(arr, left, j);
        // 左边正好k-1个数字大于主元元素
        if (j - left == k - 1) {
            return arr[j];
        } else if (j - left < k - 1) {
            // 左边不够k-1个数字来大于主元元素 说明主元过大 真正的结果在主元右边
            // 现在问题转化找右边第 k - 1 - (j - left) 大的数字
            return findKthLargestRec(arr, j + 1, right, k - 1 - (j - left));
        } else {
            // 左边超过k-1个数字大于主元元素 说明主元元素过小 真正的结果在主元左边
            // 剔除右区间,继续在左区间源数组真正第k大的数字
            return findKthLargestRec(arr, left, j - 1, k);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
