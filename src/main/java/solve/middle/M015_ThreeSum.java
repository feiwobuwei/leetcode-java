package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author wm
 * @see solve.easy.E001_TwoSum
 * @see M016_ThreeSumClosest
 */
public class M015_ThreeSum {

    private int[] arrays = {-1, 0, 1, 2, -1, -4};

    @Test
    public void test() {
        List<List<Integer>> lists = threeSum(arrays);
        System.out.println(lists);
    }

    /**
     * 双指针法
     * time:O(n^2) space O(n)
     * <p>
     * 将问题转化为在i之后的数组中,寻找nums[j]+nums[k]=-nums[i]，
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 先要排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1, high = nums.length - 1, sum = 0 - nums[i];

            while (low < high) {
                // 找到了一组解
                if (nums[low] + nums[high] == sum) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    // 去重
                    while (low < high && nums[low] == nums[low + 1]) {
                        low++;
                    }
                    while (low < high && nums[high] == nums[high - 1]) {
                        high--;
                    }
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < sum) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return res;
    }


}
