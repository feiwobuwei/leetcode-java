package solve.middle;

import org.junit.Test;

/**
 * 搜索旋转排序数组
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * @author minwei
 */
public class M033_SearchInRotatedSortedArray {

    @Test
    public void test() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int search = search(nums, 0);
        System.out.println(search);
    }

    /**
     * 二分法 - 100%
     * <p>
     * 观察所有旋转情形
     * 0　　1　　2　　 4　　5　　6　　7
     * 7　　0　　1　　 2　　4　　5　　6
     * 6　　7　　0　　 1　　2　　4　　5
     * 5　　6　　7　　 0　　1　　2　　4
     * 4　　5　　6　　 7　　0　　1　　2
     * 2　　4　　5　　 6　　7　　0　　1
     * 1　　2　　4　　 5　　6　　7　　0
     * <p>
     * 发现规律: 如果中间的数小于最右边的数，则右半段是有序的，
     * 若中间数大于最右边数，则左半段是有序的
     * <p>
     * time O(log n)
     * space O(1)
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[right]) {
                // 此时右半段有序
                if (nums[mid] < target && nums[right] >= target) {
                    // target必在mid右
                    left = mid + 1;
                } else {
                    // 其余所有情况
                    right = mid - 1;
                }
            } else {
                // 此时左半段有序
                if (nums[left] <= target && nums[mid] > target) {
                    // target必在mid左
                    right = mid - 1;
                } else {
                    // 其余所有情况
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
