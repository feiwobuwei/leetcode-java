package solve.easy;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * @author minwei
 */
public class E283_MoveZeroes {

    private  int[] nums = {0, 1, 0, 3, 12};
    private int[] nums2 = {0, 1, 0, 3, 0, 0, 12, 0, 0, 0, 0, 0};

    @Test
    public void test() {
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test2() {
        moveZeroes2(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test3() {
        moveZeroes2(nums2);
        System.out.println(Arrays.toString(nums2));
    }


    /**
     * 双指针法 -- 96.97%
     * <p>
     * time O(n)
     * space O(1)
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
                count++;
            }
        }
        for (int i = count; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 双指针法优化 -- 遇到0只判断而不交换(减少赋值操作)
     * <p>
     * time O(n)
     * space O(1)
     * 当大多数元素为 0时,比上一个要好
     */
    public void moveZeroes2(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, k++);
            }
        }
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}
