package solve.easy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>
 * 进阶：
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @author minwei
 * @see E118_PascalTriangle
 */
public class E119_PascalTriangle2 {

    @Test
    public void test() {
        // 1 不是第一行
        // [1, 5, 10, 10, 5, 1]
        List<Integer> row = getRow(5);
        System.out.println(row);

        List<Integer> row2 = getRow(33);
        System.out.println(row2);
    }

    @Test
    public void test2() {
        List<Integer> row = getRow2(5);
        System.out.println(row);

        List<Integer> row2 = getRow2(33);
        System.out.println(row2);
    }

    /**
     * 动态规划 -- 79.95%
     * <p>
     * time ：O(k^2)
     * space O(k)
     *
     * @param rowIndex 行数
     * @return 第k行
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        List<Integer> nextList = new ArrayList<>();
        // 初始情况
        list.add(1);
        // 若 rowIndex=1 则不会进入循环
        for (int i = 1; i <= rowIndex; i++) {
            // 每次先清空以前的
            nextList.clear();
            nextList.add(1);
            // list只有1个元素 也不会进入循环
            for (int j = 0; j < list.size() - 1; j++) {
                // 状态转义方程
                nextList.add(list.get(j) + list.get(j + 1));
            }
            nextList.add(1);
            list = new ArrayList<>(nextList);
        }
        return list;
    }

    /**
     * 记忆递归  --
     * <p>
     * 首先，我们定义一个函数 f(i, j)f(i,j)，它将会返回帕斯卡三角形第 i 行、第 j 列的数字。
     * 我们可以用下面的公式来表示这一递推关系：
     * f(i,j)=f(i−1,j−1)+f(i−1,j)
     *
     * @param rowIndex 行数
     * @return 第k行
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        // 初始化 +2 防止下标越界
        // 传入5 求的是6行 则要求索引从0-6 所以长度取7
        int[][] memo = new int[rowIndex + 2][rowIndex + 2];

        // 1 不作为第一行 也就是说传入1 求的其实是第2行 1 2 1
        for (int i = 1; i <= rowIndex + 1; i++) {
            result.add(helper(rowIndex + 1, i, memo));
        }
        return result;

    }

    /**
     * 返回 杨辉三角 第i行第j列的值
     * <p>
     * time ：O(k)
     * space O(k)
     *
     * @param i    第i行
     * @param j    第j列
     * @param memo 记忆数组
     */
    private int helper(int i, int j, int[][] memo) {

        // 基准情形 f(i,j)=1where j=1or j=i
        if (j == 1 || i == j) {
            return 1;
        }

        // 记忆递归 短路优化
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        // 否则该值没有出现过 需要计算
        memo[i][j] = helper(i - 1, j - 1, memo) + helper(i - 1, j, memo);
        return memo[i][j];
    }

}
