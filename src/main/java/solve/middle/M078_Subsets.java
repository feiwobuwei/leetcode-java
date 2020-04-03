package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * @author minwei
 */
public class M078_Subsets {

    int[] nums = {1, 2, 3};

    @Test
    public void test() {
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }

    /**
     * 回溯法 --34.31%
     * <p>
     * time(2^n)
     * space O(n)
     */
    public List<List<Integer>> subsets(int[] nums) {
        // 记录结果
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            helper(nums, new ArrayList<>(), i, result);
        }
        return result;
    }

    private void helper(int[] nums, List<Integer> list, int level, List<List<Integer>> result) {
        if (list.size() == level) {
            // 这里要以集合为入参另外创建一个动态数组放入结果(因为原来的容器每次都会被清空)
            result.add(new ArrayList<>(list));
            return;
        }
        for (int num : nums) {
            // 如果已经包含了该数字 或者列数尾数大于该数字 直接跳过本次循环
            if (list.contains(num) || (!list.isEmpty() && list.get(list.size() - 1) > num)) {
                continue;
            }
            list.add(num);
            helper(nums, list, level, result);
            list.remove(list.size() - 1);
        }
    }
}
