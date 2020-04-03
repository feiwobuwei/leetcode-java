package solve.hard;

import org.junit.Test;

import java.util.Arrays;

/**
 * 俄罗斯套娃问题
 * <p>
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * @author minwei
 * @see solve.middle.M300_LongestIncreasingSubsequence
 */
public class H354_RussianDollEnvelopes {

    private int[][] envelopes = {
            {5, 4},
            {6, 4},
            {6, 7},
            {2, 3}
    };

    @Test
    public void test() {
        int i = maxEnvelopes(envelopes);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = maxEnvelopes2(envelopes);
        System.out.println(i);
    }

    /**
     * LIS 问题的二维版本
     * <p>
     * time O(nlogn)
     * space O(n)
     */
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 按宽度升序排列，如果宽度一样，则按高度降序排列
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ?
                b[1] - a[1] : a[0] - b[0]);
        // 对高度数组寻找 LIS
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }

        return lengthOfLIS(height);
    }

    private int lengthOfLIS(int[] nums) {
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
                    // 更新tail[ceil]值为nums[i]
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

    /**
     * 带备忘录的DFS
     * <p>
     * time O(n2)
     * space O(n)
     */
    public int maxEnvelopes2(int[][] envelopes) {
        int result = 0;
        // 宽度和高度都是升序排列
        Arrays.sort(envelopes, (envelope1, envelope2) -> {
            if (envelope1[0] == envelope2[0]) {
                return envelope1[1] - envelope2[1];
            }
            return envelope1[0] - envelope2[0];
        });

        int n = envelopes.length;
        // 备忘录 (动态规划)
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            result = Math.max(result, getDepth(i, envelopes, dp));
        }
        return result;
    }

    /**
     * 辅助DFS -- 53.41%
     *
     * @param index     信封编号
     * @param envelopes 信封二维数组
     * @param dp        备忘录数组
     * @return 可以套入的层数
     */
    private int getDepth(int index, int[][] envelopes, int[] dp) {
        if (dp[index] != 0) {
            return dp[index];
        }
        int depth = 1;
        for (int i = 0; i < index; i++) {
            if (envelopes[index][0] > envelopes[i][0] && envelopes[index][1] > envelopes[i][1]) {
                depth = Math.max(depth, getDepth(i, envelopes, dp) + 1);
            }
        }
        dp[index] = depth;
        return depth;
    }
}
