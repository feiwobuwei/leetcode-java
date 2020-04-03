package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。
 * 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。
 * 第 i 轮，每 i 个灯泡切换一次开关。 对于第 n 轮，你只切换最后一个灯泡的开关。
 * 找出 n 轮后有多少个亮着的灯泡。
 * <p>
 * 示例:
 * 输入: 3
 * 输出: 1
 * 解释:
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 * <p>
 * 你应该返回 1，因为只有一个灯泡还亮着。
 *
 * @author minwei
 */
public class M319_BulbSwithcer {

    @Test
    public void test() {
        int i = bulbSwitch(99);
        System.out.println(i);
    }


    /**
     * 暴力法 -- 超时
     * 根据要求一步步的模拟
     * <p>
     * time O(n2)
     * sapce O(1)
     */
    public int bulbSwitch(int n) {
        Integer[] bulbs = new Integer[n + 1];
        Arrays.fill(bulbs, -1);
        bulbs[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                // 如果是因子 则翻转一次
                if (j % i == 0) {
                    bulbs[j] = -bulbs[j];
                }
            }
        }
        int result = 0;
        for (Integer ele : bulbs) {
            if (ele == 1) {
                result++;
            }
        }
        return result;
    }

    /**
     * 数学规律
     * <p>
     * 只有在轮数是该位置因数的时候才会执行翻转操作。知道位置的所有因数个数，就知道该位置翻转了多少次。
     * 除了完全平方数，因数都是成对出现的(含1乘以自身这对)，
     * 这意味着实际起到翻转作用(0->1)的，只有完全平方数而已。例如 9 只有1 3 9奇数个因数(有一组相同)
     */
    public int bulbSwitch2(int n) {
        return (int) Math.sqrt(n);
    }
}
