package solve.middle;

import solve.easy.E136_SingleNumber;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 只出现一次的数字2
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 *
 * @author minwei
 * @see E136_SingleNumber
 */
public class M137_SingleNumber2 {

    int[] nums = new int[]{0, 1, 0, 1, 0, 1, 99};

    @Test
    public void test() {
        System.out.println(singleNumber(nums));
    }

    @Test
    public void test2() {
        System.out.println(singleNumber2(nums));
    }

    @Test
    public void test3() {
        System.out.println(singleNumber3(nums));
    }

    @Test
    public void test4() {
        System.out.println(singleNumber4(nums));
    }

    /**
     * 分位计算 -- 83.40%
     * <p>
     * 对于int数，其由32位组成
     * 对于某一位，如果nums数组中所有的该位值加起来除以3余1，
     * 说明那个只出现1次的数的该位为1，否则那个只出现1次的数该位为0。
     * <p>
     * 时间复杂度是O(n)
     * 空间复杂度是O(1)
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                count += (num >> i) & 1;
            }
            if (count % 3 == 1) {
                result |= 1 << i;
            }
        }
        return result;
    }

    /**
     * 常规哈希表 -- 22.94%
     */
    public int singleNumber2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }

        }
        return -1;
    }

    /**
     * 数学规律 -- 54.15%
     * <p>
     * 3(a+b+c)-(3a+3b+c)=2c
     */
    public int singleNumber3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        long sum = 0;
        for (int num : nums) {
            set.add(num);
            sum += num;
        }
        long oneSum = 0;
        for (int n : set) {
            oneSum += n;
        }
        // 需要注意溢出问题
        return (int) ((oneSum * 3 - sum) / 2);
    }

    /**
     * 异或法的通用情形 --99.51%
     * p=3 k=1
     * <p>
     * time O(n)
     * space O(1)
     */
    public int singleNumber4(int[] nums) {
        int x1 = 0;
        int x2 = 0;
        int mask;
        for (int num : nums) {
            x2 ^= x1 & num;
            x1 ^= num;
            mask = ~(x1 & x2);
            x2 &= mask;
            x1 &= mask;
        }
        return x1;
    }


}
