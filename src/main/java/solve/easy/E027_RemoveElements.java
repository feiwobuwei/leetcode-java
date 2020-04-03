package solve.easy;

import org.junit.Test;


/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @author minwei
 */
public class E027_RemoveElements {

    private int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};

    @Test
    public void test1() {
        int len = removeElement(nums, 2);

        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    @Test
    public void test2() {
        int len = removeElement2(nums, 2);

        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 快慢指针法  —— 适用于当要删除的元素很多时
     * <p>
     * time O(n) space O(1)
     * 赋值操作的次数等于总元素个数减去要删除的元素的数量(即不符合条件元素的个数)
     * 处理完后 剩余元素顺序不变 0 1 3 0 4
     */
    private static int removeElement(int[] nums, int val) {
        // i 慢指针
        int i = 0;
        // j 快指针
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    /**
     * 双向遍历法 —— 适用于当要删除的元素很少时
     * <p>
     * time O(n) space O(1)
     * 赋值操作的次数等于要删除的元素的数量
     * 数组的顺序会发生改变 0 1 4 0 3
     */
    private static int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;

        // 双向遍历 直到指针碰头
        while (i < n) {
            // 如果当前元素匹配,将最右侧的元素赋值给它并移动右指针
            // 如果最右元素也匹配,下一次就继续将最右侧的元素赋值给它并移动右指针,否则移动左指针
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // 移动右指针
                n--;
            } else {
                // 移动左指针
                i++;
            }
        }
        return n;
    }


}