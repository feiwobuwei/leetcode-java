package solve.middle;

import java.util.Arrays;
import java.util.Random;

/**
 * 洗牌算法
 *
 * @author minwei
 */
public class M384_ShuffleAnArray {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        Solution solution = new Solution(nums);
        int[] shuffle = solution.shuffle();
        System.out.println(Arrays.toString(shuffle));

        int[] reset = solution.reset();
        System.out.println(Arrays.toString(reset));
    }


}

/**
 * 数组自带 implements Cloneable
 */
class Solution {

    private int[] arr;
    private int[] origin;

    public Solution(int[] nums) {
        arr = nums;
        origin = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return origin;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = arr.length - 1; i >= 0; i--) {
            Random random = new Random();
            // 每次产生一个0到i(不包含)的索引
            int temp = random.nextInt(i + 1);
            swap(arr, temp, i);
        }

        return arr;
    }

    private static void swap(int[] array, int i, int j) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
