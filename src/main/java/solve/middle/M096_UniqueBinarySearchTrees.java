package solve.middle;

import org.junit.Test;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * @author wm
 * @see M095_UniqueBinarySearchTrees2
 */
public class M096_UniqueBinarySearchTrees {

    @Test
    public void test() {
        int i = numTrees(3);
        System.out.println(i);

        System.out.println("=============");
        int j = numTrees(4);
        System.out.println(j);
    }


    /**
     * dp
     * <p>
     * 边界条件 f(0)=1(人为规定的 视作空节点) f(1)=1 f(2)=2
     * n = 3
     * root:1 left:0 right:2 f(0)*f(2)  2
     * root:2 left:1 right:1 f(1)*f(1)  1
     * root:3 left:2 right:0 f(2)*f(0)  2
     * <p>
     * 状态转移方程: f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)
     * 卡塔兰数 (有通项公式)
     * <p>
     * time: O(n^2)
     * space: O(n)
     */
    public int numTrees(int n) {
        int[] M = new int[n + 1];
        M[0] = 1;
        M[1] = 1;
        if (n <= 1) {
            return n;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                M[i] += M[j - 1] * M[i - j];
            }
        }
        return M[n];
    }


}
