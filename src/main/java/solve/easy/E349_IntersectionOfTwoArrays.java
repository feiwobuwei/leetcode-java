package solve.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *
 * @author minwei
 * @see E350_IntersectionOfTwoArrays2
 */
public class E349_IntersectionOfTwoArrays {

    private int[] nums1 = {4, 9, 5};
    private int[] nums2 = {9, 4, 9, 8, 4};

    @Test
    public void test() {
        int[] intersection = intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    /**
     * 内置函数
     * <p>
     * time O(m+n)
     * space O(m+n)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) {
            set1.add(n);
        }

        Set<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) {
            set2.add(n);
        }
        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int index = 0;
        for (int s : set1) {
            output[index++] = s;
        }
        return output;
    }


}
