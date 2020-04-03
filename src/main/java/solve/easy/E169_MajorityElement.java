package solve.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * @author minwei
 */
public class E169_MajorityElement {

    int[] test = new int[]{2, 2, 1, 1, 1, 2, 2};

    @Test
    public void test() {
        int i = majorityElement(test);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = majorityElement2(test);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = majorityElement3(test);
        System.out.println(i);
    }

    @Test
    public void test4() {
        int i = majorityElement4(test);
        System.out.println(i);
    }

    @Test
    public void test5() {
        int i = majorityElement5(test);
        System.out.println(i);
    }

    /**
     * 暴力法 -- 提交超时
     * <p>
     * time O(n2)
     * space O(1)
     */
    public int majorityElement(int[] nums) {

        int majorityCount = nums.length / 2;

        for (int num : nums) {
            int count = 0;
            for (int elem : nums) {
                if (elem == num) {
                    count += 1;
                }
            }
            if (count > majorityCount) {
                return num;
            }

        }
        return -1;
    }

    /**
     * 哈希表 空间换时间
     * 实测后 发现没有方法三快
     * <p>
     * time O(n)
     * space O(n)
     */
    public int majorityElement2(int[] nums) {

        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }

        Map.Entry<Integer, Integer> majorityEntry = null;

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return Objects.requireNonNull(majorityEntry).getKey();
    }

    /**
     * 排序
     * <p>
     * time O(nlgn)
     * space O(1)
     */
    public int majorityElement3(int[] nums) {
        // 底层是双路快排法
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 分治法
     * <p>
     * time O(nlgn)
     * space O(lgn)
     */
    public int majorityElement4(int[] nums) {
        return majorityElementRecursive(nums, 0, nums.length - 1);
    }

    private int majorityElementRecursive(int[] nums, int lo, int hi) {

        // 基准情形 细分到区间长度为1
        if (lo == hi) {
            return nums[lo];
        }

        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRecursive(nums, lo, mid);
        int right = majorityElementRecursive(nums, mid + 1, hi);

        // 如果左右返回值相同 说明该元素就是众数
        if (left == right) {
            return left;
        }

        // 否则左右区间需要进行比较
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        // 返回出现次数更多的元素
        return leftCount > rightCount ? left : right;
    }

    /**
     * 辅助方法 统计一个元素在数组一定区间内出现的次数
     *
     * @param nums 输入数组
     * @param num  待统计元素
     * @param lo   头指针 包含
     * @param hi   尾指针 包含
     * @return 出现次数
     */
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * Boyer-Moore 投票算法
     * <p>
     * time O(n)
     * space O(1)
     */
    public int majorityElement5(int[] nums) {
        int count = 0;
        int candidate = 0;
        // 本质 : 一对不同的数互相抵消 最后剩下的唯一一个数就是众数
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

}
