package solve.hard;

import org.junit.Test;

import java.util.Arrays;

/**
 * 鸡蛋掉落
 * <p>
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，
 * 从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * <p>
 * 提示：
 * 1 <= K <= 100
 * 1 <= N <= 10000
 *
 * @author minwei
 */
public class H887_SuperEggDrop {

    @Test
    public void test() {
        int i = superEggDrop(3, 14);
        System.out.println(i);

        // B站视频 https://www.bilibili.com/video/av16513420
        i = superEggDrop(2, 100);
        System.out.println(i);

        // 105
        System.out.println(eggCount(14, 2));
    }

    @Test
    public void test2() {
        int i = superEggDrop2(3, 14);
        System.out.println(i);

        i = superEggDrop(2, 100);
        System.out.println(i);
    }

    /**
     * 动态规划 -- 81.19%
     *
     * @param K 鸡蛋数
     * @param N 最高楼层
     * @return 需要扔的次数 。即确定 F 的值的最小移动次数
     */
    public int superEggDrop(int K, int N) {
        // i表示可以扔的次数
        int i = 1;
        while (eggCount(i, K) < N) {
            // 不够就需要增加次数
            i++;
        }
        return i;
    }

    /**
     * 辅助方法
     *
     * @param i 可以用的次数
     * @param K 鸡蛋数
     * @return 可以覆盖到的最大楼层数
     */
    public int eggCount(int i, int K) {

        // 1  : 左边的是基准情形
        // i==1 总是返回1。即无论有多少枚鸡蛋，如果只能扔一次的话，除非N=1
        // 那么可以直接确定出F(也就是1),，此时不会进入while循环。当N>1(例如2)，i 就会开始加
        // 如果i !=1 而是 k == 1 即无论有多少次机会，如果只有一枚鸡蛋的话，那么N有多高就需要扔多少次

        // 2 : 右边是 状态转移方程
        // eggCount(i - 1, K - 1)  减少一次机会 减少一枚鸡蛋
        // eggCount(i - 1, K)  减少一次机会 不减少鸡蛋

        // 单独的1表示在正解的楼层扔一次鸡蛋
        // eggCount(i - 1, K - 1) 表示这个鸡蛋碎了的情况(正解楼层下方)
        // eggCount(i - 1, K) 表示这个鸡蛋没碎的情况(正解楼层上方)

        // 3 关于正解位置,以(14,2)为例 。结合B站视频可知第一次扔蛋的正解楼层就是14
        // 然后14层以上的属于(13,2)的解决范围，14层以下的属于(13,1)的解决范围。(13,2)依此类推
        // (14,2)的结果为 (13,1)+12+11+10+...+ (1,1) +(14个1) =105


        return (i == 1 || K == 1) ? i : 1 + eggCount(i - 1, K - 1) + eggCount(i - 1, K);
    }

    // ===========================================

    /**
     * 动态规划2
     * <p>
     * 空间换时间
     *
     * @param K 鸡蛋数
     * @param N 楼层数
     * @return 需要扔的次数 。即确定 F 的值的最小移动次数
     */
    public int superEggDrop2(int K, int N) {
        int[] res = new int[K];
        Arrays.fill(res, 1);
        while (res[K - 1] < N) {
            for (int i = K - 1; i >= 1; i--) {
                res[i] = res[i] + res[i - 1] + 1;
            }
            res[0]++;
        }
        return res[0];
    }

}
