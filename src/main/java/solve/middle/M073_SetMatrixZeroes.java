package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 * <p>
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 * <p>
 * 一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 *
 * @author minwei
 */
public class M073_SetMatrixZeroes {

    int[][] nums = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
    };

    @Test
    public void test() {
        setZeroes(nums);
        System.out.println(Arrays.deepToString(nums));
    }

    @Test
    public void test2() {
        setZeroes2(nums);
        System.out.println(Arrays.deepToString(nums));
    }

    @Test
    public void test3() {
        setZeroes3(nums);
        System.out.println(Arrays.deepToString(nums));
    }

    /**
     * 暴力法 -- 96.90%
     * <p>
     * time O(mn)
     * space O(mn)
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return;
        }
        int n = matrix[0].length;
        // 访问标记
        int[][] flag = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    flag[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 横向和纵向置0
                if (flag[i][j] == 1) {
                    for (int k = 0; k < m; k++) {
                        matrix[k][j] = 0;
                    }
                    for (int k = 0; k < n; k++) {
                        matrix[i][k] = 0;
                    }
                }
            }
        }
    }

    /**
     * 改进双向标记法 -- 100%
     * <p>
     * time O(mn)
     * space O(m+n)
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return;
        }
        int n = matrix[0].length;
        int[] row = new int[m];
        int[] column = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 这一行有0出现 这一列有0出现
                    row[i] = 1;
                    column[j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (row[i] == 1) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (column[i] == 1) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    /**
     * 最优解 - 100%
     * <p>
     * 用第一行和第一列记录该行该列是否有0,作为标志位
     * 对于第一行,和第一列设置一个标志位,为了防止自己这一行(一列)也有0的情况.
     * 如果有0 留到最后处理
     * <p>
     * space O(1)
     */
    public void setZeroes3(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        // 标志第一行第一列是否有0
        boolean isFirstRowHasZero = false;
        boolean isFirstColHasZero = false;

        // 第一行是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                isFirstRowHasZero = true;
                break;
            }
        }

        // 第一列是否有零
        for (int[] matrix1 : matrix) {
            if (matrix1[0] == 0) {
                isFirstColHasZero = true;
                break;
            }
        }

        // 把第一行第一列作为标志位
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // 置0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 最后的单独处理
        if (isFirstRowHasZero) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (isFirstColHasZero) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
