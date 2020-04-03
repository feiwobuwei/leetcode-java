package solve.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * <p>
 * 输入: [3,0,1]
 * 输出: 2
 * <p>
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 *
 * @author minwei
 * @see E136_SingleNumber
 */
public class E268_MissingNumber {

    @Test
    public void test() {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int i = missingNumber(nums);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int i = missingNumber2(nums);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int i = missingNumber3(nums);
        System.out.println(i);
    }

    @Test
    public void test4() {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int i = missingNumber4(nums);
        System.out.println(i);
    }


    /**
     * 排序
     *
     * <p>
     * time O(nlgn)
     * space O(1)
     */
    public int missingNumber(int[] nums) {
        // DualPivotQuicksort
        Arrays.sort(nums);

        // 判断 n 是否出现在末位
        if (nums[nums.length - 1] != nums.length) {
            return nums.length;
        }
        // 判断 0 是否出现在首位
        else if (nums[0] != 0) {
            return 0;
        }

        // 此时缺失的数字一定在 (0, n) 中
        for (int i = 1; i < nums.length; i++) {
            int expectedNum = nums[i - 1] + 1;
            if (nums[i] != expectedNum) {
                return expectedNum;
            }
        }

        // 未缺失任何数字
        return -1;

    }

    /**
     * 哈希表 -- 空间复杂度不符合题意
     * <p>
     * time O(n)
     * space O(n)
     */
    public int missingNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!set.contains(number)) {
                return number;
            }
        }
        return -1;
    }

    /**
     * 异或
     * <p>
     * time O(n)
     * space O(1)
     */
    public int missingNumber3(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    /**
     * 数学 --  n(n+1)/2
     * <p>
     * time O(n)
     * space O(1)
     */
    public int missingNumber4(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }


}
