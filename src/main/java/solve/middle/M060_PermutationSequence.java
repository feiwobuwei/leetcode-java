package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 *
 * @author minwei
 * @see M046_Permutations
 */
public class M060_PermutationSequence {

    @Test
    public void test() {
        String permutation = getPermutation(4, 14);
        System.out.println(permutation);
    }

    @Test
    public void test2() {
        String permutation = getPermutation2(3, 3);
        System.out.println(permutation);
    }

    /**
     * 递归 -- 100%
     * <p>
     * 以 n = 4, k = 14 的情况进行分析
     * 由1开头的排列有6个，由2开头的排列、由3开头的排列、由4开头的排列也均有6个。
     * 当我们寻找第14个排列时，可以确定第一个数字是3。然后在{1, 2, 4}中寻找第2个排列。
     *
     */
    public String getPermutation(int n, int k) {
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        // 处理完后 数组就自动变为了该字符串
        getPermutation(array, 0, k);
        StringBuilder result = new StringBuilder();
        for (int ele : array) {
            result.append(ele);
        }
        return result.toString();
    }

    private void getPermutation(int[] array, int index, int k) {
        if (k == 1) {
            return;
        }
        int i = index;
        int level = factorial(array.length - 1 - index);
        // 14-6-6
        for (; i < array.length; i++) {
            if (k - level < 1) {
                break;
            }
            k -= level;
        }
        // 循环结束i为2 提出i所在元素3 其左边到index为止的所有元素右移一位 然后temp放到index原来的位置
        int temp = array[i];
        if (i - index >= 0) {
            System.arraycopy(array, index, array, index + 1, i - index);
        }
        array[index] = temp;
        //
        getPermutation(array, index + 1, k);
    }

    /**
     * 计算阶乘的辅助方法
     */
    private int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * 全排列 加 字典序排列 -- 提交超时
     */
    public String getPermutation2(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        return permute(nums).get(k - 1);
    }

    public List<String> permute(int[] nums) {
        List<String> result = new ArrayList<>();
        permute(nums, new StringBuilder(), result);
        return result;
    }

    private void permute(int[] nums, StringBuilder sb, List<String> result) {
        int n = nums.length;
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }
        for (int num : nums) {
            if (contains(sb, num)) {
                continue;
            }
            sb.append(num);
            permute(nums, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private boolean contains(StringBuilder stringBuilder, int k) {
        int i = 0;
        while (i < stringBuilder.length()) {
            if (stringBuilder.charAt(i) == k + '0') {
                return true;
            }
            i++;
        }
        return false;
    }

}
