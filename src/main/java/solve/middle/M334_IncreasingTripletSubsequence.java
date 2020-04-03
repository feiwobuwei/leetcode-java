package solve.middle;

import org.junit.Test;

/**
 * 递增的三元子序列
 * <p>
 * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
 * 数学表达式如下:
 * <p>
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [5,4,3,2,1]
 * 输出: false
 *
 * @author minwei
 */
public class M334_IncreasingTripletSubsequence {

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 5};
        boolean b = increasingTriplet(nums);
        System.out.println(b);
    }

    @Test
    public void test2() {
        int[] nums = {5, 4, 3, 2, 1};
        boolean b = increasingTriplet(nums);
        System.out.println(b);
    }

    /**
     * one-pass -- 99.73%
     * <p>
     * time O(n)
     * space O(1)
     */
    public boolean increasingTriplet(int[] nums) {
        int num1 = Integer.MAX_VALUE;
        int num2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num1 >= num) {
                // 在从左到右的遍历过程中num1必然是一直减小的
                num1 = num;
            } else {
                if (num2 >= num) {
                    // 此时有num1 < num
                    // 在从左到右的遍历过程中num2必然是一直减小的
                    // 必然有num1 < num2
                    num2 = num;
                } else {
                    // 已经有了num1 < num2，此时又有num2 < num
                    // 故有num1 < num2 < num
                    return true;
                }
            }
        }
        return false;
    }
}
