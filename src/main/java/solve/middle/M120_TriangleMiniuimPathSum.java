package solve.middle;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <pre>
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * </pre>
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * @author minwei
 */
public class M120_TriangleMiniuimPathSum {

    private int[][] triangle0 = {
            {2},
            {3, 4},
            {6, 5, 7},
            {4, 1, 8, 3}
    };

    private List<List<Integer>> triangle;

    @Before
    public void prepared() {
        triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));

    }

    @Test
    public void test() {
        System.out.println(Arrays.deepToString(triangle0));
        triangle.forEach(System.out::println);
    }

    @Test
    public void test1() {
        int i = minimumTotal(triangle);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = minimumTotal2(triangle);
        System.out.println(i);
    }


    /**
     * 动态规划 -- 96.98%
     * 自底而上
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] minlen = new int[row + 1];
        for (int level = row - 1; level >= 0; level--) {
            // 第i行有i+1个数字
            for (int i = 0; i <= level; i++) {
                minlen[i] = Math.min(minlen[i], minlen[i + 1]) + triangle.get(level).get(i);
            }
        }
        return minlen[0];

    }

    private int row;

    /**
     * 递归带备忘录
     * 自顶而下
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        row = triangle.size();
        Integer[][] memo = new Integer[row][row];
        return helper(0, 0, triangle, memo);
    }

    private int helper(int level, int c, List<List<Integer>> triangle, Integer[][] memo) {
        if (memo[level][c] != null) {
            return memo[level][c];
        }
        if (level == row - 1) {
            return memo[level][c] = triangle.get(level).get(c);
        }
        int left = helper(level + 1, c, triangle, memo);
        int right = helper(level + 1, c + 1, triangle, memo);
        return memo[level][c] = Math.min(left, right) + triangle.get(level).get(c);
    }

}
