package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 比特位计数
 * <p>
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * <p>
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @author minwei
 */
public class M338_CountingBits {

    @Test
    public void test() {
        int[] ints = countBits(5);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void test2() {
        int[] ints = countBits2(5);
        System.out.println(Arrays.toString(ints));
    }


    /**
     * 动态规划 -- 99.18%
     * <p>
     * 状态定义：f(x) -- 数组中索引为x处的值
     * 状态转移：
     * （1）如果x是偶数，f(x) = f(x / 2)。因为对于偶数来说，
     * 其乘以2只是在所有位左移1位并在右边新添一个0，因此其1的个数不会发生变化。
     * （2）如果x是奇数，f(x) = f(x - 1)。奇数的1的个数一定和比它小的最大偶数的1的个数相同。
     * <p>
     * 时间复杂度和空间复杂度均是O(n)。
     */
    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        } else if (num == 1) {
            return new int[]{0, 1};
        }
        int[] result = new int[num + 1];
        result[0] = 0;
        result[1] = 1;
        for (int i = 2; i < num + 1; i++) {
            if (i % 2 == 0) {
                result[i] = result[i / 2];
            } else {
                result[i] = result[i - 1] + 1;
            }
        }
        return result;
    }

    /**
     * 位运算 -- 100%
     * <p>
     * 数学规律
     * i中1的个数一定比i & (i - 1)中1的个数多1个
     * <p>
     * time O(n)
     */
    public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;
        for (int i = 1; i < num + 1; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

}
