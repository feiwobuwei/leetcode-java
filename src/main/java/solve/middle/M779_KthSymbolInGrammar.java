package solve.middle;

import org.junit.Test;

/**
 * 第K个语法符号
 * <p>
 * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 * <p>
 * 例子:
 * 输入: N = 1, K = 1
 * 输出: 0
 * <p>
 * 输入: N = 2, K = 1
 * 输出: 0
 * <p>
 * 输入: N = 2, K = 2
 * 输出: 1
 * <p>
 * 输入: N = 4, K = 5
 * 输出: 1
 * <p>
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 第三行: 0110
 * 第四行: 01101001
 * <p>
 * 注意：
 * N 的范围 [1, 30].
 * K 的范围 [1, 2^(N-1)].
 *
 * @author wm
 */
public class M779_KthSymbolInGrammar {

    @Test
    public void test() {
        // 1
        System.out.println(kthGrammar(4, 5));
        // 0
        System.out.println(kthGrammar(3, 4));
        // 0
        System.out.println(kthGrammar(5, 6));
    }

    @Test
    public void test2() {
        System.out.println(kthGrammar2(4, 5));
        System.out.println(kthGrammar2(3, 4));
        System.out.println(kthGrammar2(5, 6));
    }

    /**
     * 递归
     * <p>
     * 找规律
     * 第 K 位的父位应该是上一行的第 (K+1) / 2 位。(K从1开始) (举例:本行的1和2顺位都是上一行的1)
     * 如果父位是 0，那么这一位就是 1 - (K%2)。(0 -> 01 )
     * 如果父位是 1，那么这一位就是 K%2。 (1 -> 10 )
     * <p>
     * 时间复杂度：O(N)  需要 N-1步。
     * 空间复杂度：O(1)
     *
     * @param N 第N行
     * @param K 第K个数
     * @return 该位置的值 0 或者 1
     */
    public int kthGrammar(int N, int K) {
        // 基准情形
        if (N == 1) {
            return 0;
        }

        // 父的值
        int tmp = kthGrammar(N - 1, (K + 1) / 2);

        // 如果父是1
        if (tmp == 1) {
            return K % 2;
        } else {
            // 如果父是0
            return 1 - K % 2;
        }
    }

    // ================= 方法二的进化 =============== //

    /**
     * 可以发现： 后半部分总是与前半部分相反
     * 如果 K 在后半部分，那么我们可以将 K -= (1 << N-2) 设为前半部分，然后翻转得到最终答案。
     */
    public int kthGrammar2(int N, int K) {
        if (N == 1) {
            return 0;
        }

        // 1 << N - 2 是 2^(n-3) 。 先加减再位移
        // 第3行有4个数 一半位置是2
        // 第4行有8个数 一半位置是4

        // 每一行的前半部分总是与上行完全相同
        if (K <= (1 << N - 2)) {
            return kthGrammar2(N - 1, K);
        }

        // 例如第4行, K =6 那么k先到2的位置 然后把结果取反(异或 等效于取反)
        return kthGrammar2(N - 1, K - (1 << N - 2)) ^ 1;
    }

}
