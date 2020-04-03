package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * <p>
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * @author minwei
 * @see M059_SpiralMatrix2
 */
public class M054_SpiralMatrix {

    private int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
    };

    /*
        [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
     */

    @Test
    public void test() {
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }

    @Test
    public void test2() {
        List<Integer> list = spiralOrder2(matrix);
        System.out.println(list);
    }

    /**
     * 规律法
     * 先计算要绕的圈数，再一圈一圈绕
     * 访问过的地方做标记
     * <p>
     * time O(mn)
     * space O(mn)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return list;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return list;
        }
        boolean[][] visited = new boolean[m][n];
        int turn = Math.min(m, n) % 2 == 0 ? Math.min(m, n) / 2 : Math.min(m, n) / 2 + 1;
        for (int k = 0; k < turn; k++) {
            // 右行
            for (int i = 0; i < n; i++) {
                if (!visited[k][i]) {
                    list.add(matrix[k][i]);
                    visited[k][i] = true;
                }
            }
            // 下行
            for (int i = 0; i < m; i++) {
                if (!visited[i][n - 1 - k]) {
                    list.add(matrix[i][n - 1 - k]);
                    visited[i][n - 1 - k] = true;
                }
            }
            // 左行
            for (int i = n - 1; i >= 0; i--) {
                if (!visited[m - 1 - k][i]) {
                    list.add(matrix[m - 1 - k][i]);
                    visited[m - 1 - k][i] = true;
                }
            }
            // 上行
            for (int i = m - 1; i >= 0; i--) {
                if (!visited[i][k]) {
                    list.add(matrix[i][k]);
                    visited[i][k] = true;
                }
            }
        }
        return list;
    }

    /**
     * 不使用访问矩阵 而是四个边界变量
     * <p>
     * 降低空间复杂度
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return list;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return list;
        }
        // 上下左右四个边界指针
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        while (top <= bottom && left <= right) {
            // 右行
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            // 上边界下移
            top++;
            // 下行
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            // 右边界左移
            right--;
            // 左行
            if (bottom >= top) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                // 下边界上移
                bottom--;
            }

            // 上行
            if (right >= left) {
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                // 左边界右移
                left++;
            }

        }
        return list;
    }

}
