package solve.easy;

import org.junit.Test;

/**
 * 搜索插入位置
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * @author wm
 * @see E278_FirstBadVersion
 */
public class E035_SearchInsertPosition {

    int[] test = new int[]{1, 3, 5, 6};
    int[] test2 = new int[]{2, 3, 4, 5};

    @Test
    public void test() {
        int i = searchInsert(test, 5);
        System.out.println(i);

        i = searchInsert(test, 4);
        System.out.println(i);

        i = searchInsert(test2, 1);
        System.out.println(i);

        i = searchInsert(test2, 6);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = searchInsert2(test, 5);
        System.out.println(i);

        i = searchInsert2(test, 4);
        System.out.println(i);

        i = searchInsert2(test2, 1);
        System.out.println(i);

        i = searchInsert2(test2, 6);
        System.out.println(i);
    }

    /**
     * 暴力法
     * <p>
     * time O(n);
     */
    public int searchInsert(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 二分法-迭代法
     * <p>
     * time O(lgn)
     * 执行用时 : 0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     */
    public int searchInsert2(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return nums.length;
    }

}
