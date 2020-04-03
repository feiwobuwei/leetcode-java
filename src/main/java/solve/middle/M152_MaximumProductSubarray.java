package solve.middle;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * @author wm
 */
public class M152_MaximumProductSubarray {

    private int[] input = new int[]{2, 3, -2, 4};
    private int[] input2 = new int[]{2, 3, -2, 4, -2, -5};

    @Test
    public void test() {
        System.out.println(maxProduct(input));
        System.out.println(maxProduct(input2));
    }

    @Test
    public void test2() {
        System.out.println(maxProduct2(input));
        System.out.println(maxProduct2(input2));
    }

    /**
     * 正向反向各遍历一次
     * <p>
     * time O(n)
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 1;
        int res = nums[0];

        for (int i = 0; i < nums.length; i++) {
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }
        }

        // 然后从后往前找
        max = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }
        }
        return res;
    }

    /**
     * 暴力法 -- 8.46%
     * <p>
     * time O(n^2)
     */
    public int maxProduct2(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        // 全局最大乘积
        long maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            // 本轮最大乘积
            long curProduct = Integer.MIN_VALUE;
            long product = 1L;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                curProduct = Math.max(product, curProduct);
            }
            maxProduct = Math.max(maxProduct, curProduct);
        }

        return (int) maxProduct;
    }

}
