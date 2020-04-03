package solve.easy;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * @author minwei
 * @see E001_TwoSum
 */
public class E167_TwoSum2 {

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
     * 双指针法 -- 99.78%
     * <p>
     * time O(n)
     * space O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] indexes = new int[2];
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                indexes[0] = i + 1;
                indexes[1] = j + 1;
                break;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return indexes;
    }

    /**
     * 二分法
     * <p>
     * time O(nlogn)
     * space O(n)
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] indexes = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int temp = target - numbers[i];
            int index = find(numbers, i + 1, numbers.length - 1, temp);
            if (index != -1) {
                indexes[0] = i + 1;
                indexes[1] = index + 1;
            }
        }
        return indexes;
    }

    private int find(int[] arr, int left, int right, int temp) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == temp) {
            return mid;
        } else if (arr[mid] > temp) {
            return find(arr, left, mid - 1, temp);
        } else {
            return find(arr, mid + 1, right, temp);
        }
    }


}
