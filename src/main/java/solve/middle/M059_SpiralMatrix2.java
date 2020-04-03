package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 螺旋矩阵2
 * <p>
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * * <p>
 * * 示例 1:
 * * 输入: 3
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 *
 * @author minwei
 * @see M054_SpiralMatrix
 */
public class M059_SpiralMatrix2 {

    @Test
    public void test() {
        int[][] ints = generateMatrix(5);
        System.out.println(Arrays.deepToString(ints));
    }

    /**
     * 类似M054方法二
     * 那个取数字 本题是填数字
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        // 依然是四个边界控制指针
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                result[top][i] = num++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result[i][right] = num++;
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[bottom][i] = num++;
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[i][left] = num++;
                }
                left++;
            }
        }
        return result;
    }


}
