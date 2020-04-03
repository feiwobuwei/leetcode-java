package solve.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 两数之和
 *
 * @author minwei
 * @see E167_TwoSum2
 * @see solve.middle.M015_ThreeSum
 */
public class E001_TwoSum {

    private int[] nums = {2, 7, 11, 15};

    @Test
    public void test() {
        int[] ints = twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void test2() {
        int[] ints = twoSum2(nums, 9);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 哈希表
     * 空间换时间
     * <p>
     * time：O(n)
     * space: O(n)
     */
    private int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{-1, -1};
        }

        int[] res = new int[]{-1, -1};
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     * 两次哈希表
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // 防止2+2=4这种情形
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[]{-1, -1};
    }

}
