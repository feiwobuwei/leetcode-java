package solve.easy;

import java.util.*;

/**
 * @author minwei
 */
public class E217_ContainsDublicate {

    /**
     * 常规法
     * <p>
     * time O(n)
     * space O(n)
     */
    public boolean containsDuplicate(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            // 键是唯一的
            if (map.keySet().contains(nums[i])) {
                return true;
            }
            map.put(nums[i], i);
        }

        return false;

    }

    /**
     * 排序
     * <p>
     * time O(n*lgn)
     * space O(n*lgn)
     */
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;

    }


    /**
     * 集合
     * time O(n)
     * space O(n)
     */
    public boolean containsDuplicate3(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x : nums) {
            if (set.contains(x)) {
                return true;
            }
            set.add(x);
        }
        return false;

    }
}
