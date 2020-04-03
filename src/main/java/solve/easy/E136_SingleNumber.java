package solve.easy;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。
 * 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author minwei
 * @see solve.middle.M137_SingleNumber2
 * @see solve.middle.M260_SingleNumber3
 */
public class E136_SingleNumber {

    int[] nums = {4, 2, 1, 4, 2};

    @Test
    public void test() {
        System.out.println(singleNumber(nums));
    }

    @Test
    public void test2() {
        System.out.println(singleNumber2(nums));
    }

    @Test
    public void test3() {
        System.out.println(singleNumber3(nums));
    }

    /**
     * 异或法 XOR
     * <p>
     * time O(n) space O(1)
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    /**
     * 全部元素求和 某元素偶数次出现加负号
     * 这样相同的数字会被抵消掉
     */
    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;

        for (int ele : nums) {
            if (set.contains(ele)) {
                set.remove(ele);
                ele = -ele;
            } else {
                set.add(ele);
            }
            sum += ele;
        }
        return sum;
    }

    /**
     * 数学 2 * (a + b + c) - (a + a + b + b + c)=c
     * <p>
     * time O(n) space O(n)
     */
    public static int singleNumber3(int[] nums) {

        int sum = 0;
        int identicalSum = 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                identicalSum += num;
            }
            sum += num;
        }

        return 2 * identicalSum - sum;

    }
}
