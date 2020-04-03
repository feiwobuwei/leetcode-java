package solve.middle;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * @author minwei
 * @see M142_LinkedListCycle2
 */
public class M287_FindTheDuplicateNumber {

    int[] nums = {3, 1, 3, 4, 2};

    @Test
    public void test() {
        int duplicate = findDuplicate(nums);
        System.out.println(duplicate);
    }

    @Test
    public void test2() {
        int duplicate = findDuplicate2(nums);
        System.out.println(duplicate);
    }

    @Test
    public void test3() {
        int duplicate = findDuplicate3(nums);
        System.out.println(duplicate);
    }

    @Test
    public void test4() {
        int duplicate = findDuplicate4(nums);
        System.out.println(duplicate);
    }

    /**
     * 快慢指针法 -- 100%
     * <p>
     * 类似环形链表
     * <p>
     * time O(n)
     * space O(1)
     */
    public int findDuplicate(int[] nums) {
        int fast = 0;
        int slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) {
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }

    }

    /**
     * 排序 -- 61.40%
     * <p>
     * time O(nlgn)
     * space O(1)
     */
    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 哈希表 58.96%
     * <p>
     * time O(n)
     * space O(1)
     */
    public int findDuplicate3(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1;
    }

    /**
     * 把元素放到它应该在的索引处 -- 70.78%
     * 遍历到最后 由于正确的位置已经有元素 因此另外一个重复的就换不过来了
     * <p>
     * time O(nlgn)
     * space O(1)
     */
    public int findDuplicate4(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        return nums[nums.length - 1];
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
