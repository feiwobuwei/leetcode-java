package solve.middle;

import org.junit.Test;

/**
 * 最长上升子序列 LIS -- Longest Increasing Subsequence
 * <p>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n^2) 。
 * <p>
 * 进阶: 你能将算法的时间复杂度降低到 O(nlogn) 吗?
 *
 * @author minwei
 */
public class M300_LongestIncreasingSubsequence {

    int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

    @Test
    public void test() {
        int i = lengthOfLIS(nums);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = lengthOfLIS2(nums);
        System.out.println(i);
    }

    /**
     * 动态规划 -- 19.56%
     * <p>
     * 状态定义：dp[i]，以索引i处数字结尾的最长上升子序列的长度。
     * 状态转移：
     * dp[0] = 1
     * 当i > 0时，dp[i] = Math.max(dp[j] + 1, dp[i])，其中j∈[0, i)且nums[i] > nums[j]
     * <p>
     * 时间复杂度是O(n^2)
     * 空间复杂度是O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];

        dp[0] = 1;
        int result = dp[0];
        for (int i = 1; i < n; i++) {
            // 每次初始值为1 即自身构成的子集
            dp[i] = 1;
            // 判断前面有几个数小于自己
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 当前最大值
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
                // 最终最大值(因为最终结果并不一定是dp[n])
                result = Math.max(result, dp[i]);
            }
        }
        return result;
    }

    /**
     * 二分查找法 -- 99.60%
     * <p>
     * 首先，创建需要一个n行的二维数组，其中
     * 第0行记录长度为1的一个"上升子序列"，
     * 第1行记录长度为2的一个"上升子序列"，...，
     * 第n - 1行记录长度为n的一个"上升子序列"。
     * <p>
     * 所有的这些子序列满足一个条件：
     * 它们的"结尾"是所有相同长度的"上升子序列"里面最小的。
     * <p>
     * 以数组[10,9,2,5,3,7,101,18]为例进行说明：
     * 首先需要一个8行的数组，初始均为空：
     * [[], [], [], [], [], [], [], []]
     * <p>
     * （1）遍历第0个元素——10，此时我们填充第0行：
     * [[10], [], [], [], [], [], [], []]
     * （2）遍历第1个元素——9，此时我们的数组如下：
     * [[9], [], [], [], [], [], [], []]
     * （3）遍历第2个元素——2，此时我们的数组如下：
     * [[2], [], [], [], [], [], [], []]
     * （4）遍历第3个元素——5，5比2大，此时我们填充第1行：
     * [[2], [2, 5], [], [], [], [], [], []]
     * （5）遍历第4个元素——3，3比2大比5小，此时我们的数组如下：
     * [[2], [2, 3], [], [], [], [], [], []]
     * （6）遍历第5个元素——7，7比3大，此时我们填充第2行：
     * [[2], [2, 3], [2, 3, 7], [], [], [], [], []]
     * （7）遍历第6个元素——101，101比7大，此时我们填充第3行：
     * [[2], [2, 3], [2, 3, 7], [2, 3, 7, 101], [], [], [], []]
     * （8）遍历第7个元素——18，18比7大，比101小，此时我们的数组如下：
     * [[2], [2, 3], [2, 3, 7], [2, 3, 7, 18], [], [], [], []]
     * <p>
     * 遍历完成后，发现总共填充了3行，因此最长子序列长度就是4。
     * 在整个过程中只用到了第i行的最后一个元素，因此可以用一个一维数组tail。
     * <p>
     * time O(nlogn)
     * space O(n)。
     */
    public int lengthOfLIS2(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0) {
            return 0;
        }
        int[] tail = new int[n];
        tail[0] = nums[0];
        // end表示有序数组tail的最后一个已经赋值元素的索引
        int end = 0;
        for (int i = 1; i < n; i++) {
            // 得到一个长度+1的新的上升子序列
            if (nums[i] > tail[end]) {
                tail[++end] = nums[i];
            } else {
                // ceil()函数二分查找，在tail数组[0, end]范围内找到大于nums[i]的最小元素的索引
                int ceil = ceil(nums[i], tail, end);
                // 如果nums[i]在tail数组的[0, end]范围内已经存在，什么都不做
                if (!(ceil - 1 >= 0 && tail[ceil - 1] == nums[i])) {
                    // 更新tail[left]值为nums[i]
                    tail[ceil] = nums[i];
                }
            }
        }
        return end + 1;
    }

    private int ceil(int target, int[] tail, int end) {
        int left = 0, right = end + 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (tail[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
