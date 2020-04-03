package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 旋转图像
 * <p>
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * @author minwei
 */
public class M048_RotateImage {

    private int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    @Test
    public void test() {
        System.out.println(Arrays.deepToString(matrix));
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    @Test
    public void test2() {
        System.out.println(Arrays.deepToString(matrix));
        rotate2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 先转置矩阵，然后反转每一行。
     * <p>
     * time O(n^2) 已经是最优复杂度
     * space O(1)
     *
     * @see solve.easy.E189_RotateArray#rotate 反转数组
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }

        // 反转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swapRow(matrix, i, j, n - j - 1);
            }
        }

    }

    /**
     * 反转二维数组的某一排
     */
    private void swapRow(int[][] nums, int row, int i, int j) {
        int temp = nums[row][i];
        nums[row][i] = nums[row][j];
        nums[row][j] = temp;
    }

    /**
     * 找规律 -- 击败了 100% 的用户
     * <p>
     * 从最外层一直旋转到最内层，直至不能旋转。 对于n阶矩阵，需要旋转的层数是n / 2。
     * 四阶矩阵 分析第一排
     * 对于(0, 0)位置，有如下旋转规律：
     * (0, 0) -> (0, 3)
     * (0, 3) -> (3, 3)
     * (3, 3) -> (3, 0)
     * (3, 0) -> (0, 0)
     * 对于(0, 1)位置，有如下旋转规律：
     * (0, 1) -> (1, 3)
     * (1, 3) -> (3, 2)
     * (3, 2) -> (2, 0)
     * (2, 0) -> (0, 1)
     * 同理，对于(0, 2)位置，有如下旋转规律：
     * (0, 2) -> (2, 3)
     * (2, 3) -> (3, 1)
     * (3, 1) -> (1, 0)
     * (1, 0) -> (0, 2)
     * 对于(0, 3)位置，已经在处理(0, 0)位置的旋转中涉及了该值,停止本层的旋转
     * <p>
     * time O(n^2) space O(1)
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int k = 0; k <= n / 2; k++) {
            for (int i = k; i < n - 1 - k; i++) {
                // 四个值的交换
                int temp = matrix[k][i];
                // 参考方法一 行变列(转置) + 列变行后(转置)需要再反转一下
                matrix[k][i] = matrix[n - 1 - i][k];
                matrix[n - 1 - i][k] = matrix[n - 1 - k][n - 1 - i];
                matrix[n - 1 - k][n - 1 - i] = matrix[i][n - 1 - k];
                matrix[i][n - 1 - k] = temp;
            }
        }
    }


}
