package solve.middle;

import org.junit.Test;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @author wm
 */
public class M031_NextPermutation {

    @Test
    public void test() {
        int[] nums = {3, 4, 1, 2};
        nextPermutation(nums);
    }

    @Test
    public void test2() {
        int[] nums = {1, 5, 8, 4, 7, 3, 5, 6, 1};
        nextPermutation(nums);
    }

    /**
     * 一遍扫描
     * <p>
     * 1 对于任何给定序列的降序，没有可能的下一个更大的排列(此时i,j都会减到0,相当于直接反转自身)
     * 2 寻找指针i,i位于倒数第2个位置开始从右往左扫描 第一次出现下降的位置。
     * (1)如果i就是倒数第2个位置,也就是倒数第2个元素小于倒数第一个元素 那么直接互换这2者就是下一个排列
     * (此时反转区也只有倒数第一位置的一个元素,相当于reverse不做任何操作)
     * (2)如果i不是倒数第2个位置,那么而i的右边是升序(从右往左看)。此时需要把i-1位置的元素与
     * 位于i右侧区域的数字中比它大的最小数字(ceiling)进行交换(交换完右边还是升序,因为a[i]比它两边元素都大)
     * 3 将a[i-1]右边的数字按升序排列 来形成最小的排列。(此时i到尾端为反转区)
     * <p>
     * time: O(n) space: O(1)
     */
    public void nextPermutation(int[] nums) {
        // 倒数第2个元素
        int i = nums.length - 2;
        // 从右往左扫描 i指针停留在从右往左第一次出现下降的位置
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            // 倒数第一个元素
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 最后倒转
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
