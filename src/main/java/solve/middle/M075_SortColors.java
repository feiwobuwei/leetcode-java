package solve.middle;

import org.junit.Test;

/**
 * 颜色分类
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * <p>
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * @author minwei
 */
public class M075_SortColors {

    @Test
    public void test() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
    }

    /**
     * 双路快排 维持三个区间 小于主元 等于主元 大于主元
     */
    public void sortColors(int[] nums) {
        int lessThan = -1;
        int greaterThan = nums.length;
        int i = 0;
        while (i < greaterThan) {
            if (nums[i] == 0) {
                swap(nums, i, lessThan + 1);
                lessThan++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, greaterThan - 1);
                greaterThan--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
