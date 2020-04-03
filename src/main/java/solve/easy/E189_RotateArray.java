package solve.easy;

import org.junit.Test;

import java.util.Arrays;

/**
 * 旋转数组
 * <p>
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * @author minwei
 */
public class E189_RotateArray {

    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    @Test
    public void test() {
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test2() {
        rotate2(nums, 10);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test3() {
        rotate3(nums, 10);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test4() {
        rotate4(nums, 10);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 暴力法 - 所有元素每次向右移动一位 移动k次
     * 超时
     * <p>
     * time O(n*(k%n))
     * space O(1)
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < nums.length; j++) {
                swap(nums, nums.length - 1, j);
            }
        }
    }

    /**
     * 可以经过下面几个步骤得到：
     * （1）反转原数组，得到[7, 6, 5, 4, 3, 2, 1]。
     * （2）反转[0, 2]范围内的元素，得到[5, 6, 7, 4, 3, 2, 1]。
     * （3）反转[3, 6]范围内的元素，得到[5, 6, 7, 1, 2, 3, 4]。
     * <p>
     * 时间复杂度是O(n)，其中n为nums数组的长度。
     * 空间复杂度是O(1)。
     */
    public void rotate2(int[] nums, int k) {
        if (0 == nums.length) {
            return;
        }
        // 求余数
        k = k % nums.length;
        if (0 == k) {
            return;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        int i = left;
        int j = right;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /**
     * 另一种暴力法 -- 击败了 33.79% 的用户
     * 提出尾元素 然后把前面所有元素右移一位 再把尾元素放到头部
     * <p>
     * 时间复杂度是O(nk)
     * 空间复杂度是O(1)
     */
    public void rotate3(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        k %= nums.length;
        for (int i = 0; i < k; i++) {
            int temp = nums[nums.length - 1];
            System.arraycopy(nums, 0, nums, 1, nums.length - 1);
            nums[0] = temp;
        }
    }

    /**
     * 三次拷贝 -- 非原地算法 不符合题意
     * <p>
     * time O(n)
     * space O(n)
     */
    public void rotate4(int[] nums, int k) {
        int len = nums.length;
        int[] temp = new int[len];
        k %= len;
        System.arraycopy(nums, len - k, temp, 0, k);
        System.arraycopy(nums, 0, temp, k, len - k);
        System.arraycopy(temp, 0, nums, 0, len);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
