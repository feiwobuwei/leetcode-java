package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * @author minwei
 */
public class M034_FindFirstAndLastPositionOfElementInSortedArray {

    @Test
    public void test() {
        int[] nums = {5, 7, 7, 8, 8, 8, 10};
        int[] ints = searchRange(nums, 8);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void test2() {
        int[] nums = {2, 2};
        int[] ints = searchRange(nums, 3);
        System.out.println(Arrays.toString(ints));
    }


    /**
     * 标志二分法 -- 98.23%
     *
     */
    public int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] result = new int[2];

        return searchRangeRec(nums, 0, nums.length - 1, target, result);

    }

    private int[] searchRangeRec(int[] arr, int left, int right, int value, int[] result) {

        if (left > right) {
            // 如果没有目标值
            return new int[]{-1, -1};
        }

        int mid = (right - left) / 2 + left;
        int midVal = arr[mid];

        if (value > midVal) {
            // 向右递归
            return searchRangeRec(arr, mid + 1, right, value, result);
        } else if (value < midVal) {
            // 向左递归
            return searchRangeRec(arr, left, mid - 1, value, result);
        } else {
            int temp = mid;
            // 一直循环到该值左边的值与目标值不同
            while (temp >= 0 && arr[temp] == value) {
                temp--;
            }
            result[0] = temp + 1;

            temp = mid;
            // 一直循环到该值右边的值与目标值不同
            while (temp <= arr.length - 1 && arr[temp] == value) {
                temp++;
            }
            result[1] = temp - 1;
            return result;
        }
    }
}
