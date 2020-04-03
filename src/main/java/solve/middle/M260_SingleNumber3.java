package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
 * 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 * @author minwei
 * @see solve.easy.E136_SingleNumber
 */
public class M260_SingleNumber3 {

    @Test
    public void test() {
        int[] nums = {1, 2, 1, 17, 2, 5, 5, 4, 8, 4, 8, 101};
        int[] ints = singleNumber(nums);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 位运算 -- 100%
     * <p>
     * time O(n)
     * space O(1)
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        int[] result = new int[2];
        for (int ele : nums) {
            xor ^= ele;
        }
        int temp = xor;
        int k = 0;

        while ((temp & 1) != 1) {
            temp = temp >> 1;
            k++;
        }
        for (int num : nums) {
            if ((num >> k & 1) != 1) {
                result[0] ^= num;
            }
        }
        result[1] = result[0] ^ xor;
        return result;
    }

}
