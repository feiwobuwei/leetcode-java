package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），
 * 请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * <p>
 * 示例:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * <p>
 * 输出:  [1,2,4,7,5,3,6,8,9]
 */
public class M498_DiagonalTraverse {

    private int[][] input = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    private int[][] input2 = new int[][]{
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20}
    };

    private int[][] input3 = new int[][]{};

    private int[][] input4 = new int[][]{
            {3},
            {2},
    };

    private int[][] input5 = new int[][]{
            {3, 2}
    };


    @Test
    public void test() {
        Arrays.stream(input).forEach(i -> System.out.println(Arrays.toString(i)));
        System.out.println("============================");
        int[] diagonalOrder = findDiagonalOrder(input);
        System.out.println(Arrays.toString(diagonalOrder));

    }

    @Test
    public void test2() {
        int[] diagonalOrder = findDiagonalOrder(input2);
        System.out.println(Arrays.toString(diagonalOrder));
    }

    @Test
    public void test3() {
        int[] diagonalOrder = findDiagonalOrder(input3);
        System.out.println(Arrays.toString(diagonalOrder));
    }

    @Test
    public void test4() {
        Arrays.stream(input4).forEach(i -> System.out.println(Arrays.toString(i)));
        System.out.println("============================");
        int[] diagonalOrder = findDiagonalOrder(input4);
        System.out.println(Arrays.toString(diagonalOrder));

    }

    @Test
    public void test5() {
        int[] diagonalOrder = findDiagonalOrder(input5);
        System.out.println(Arrays.toString(diagonalOrder));

    }


    /**
     * 双指针法 -- 97.61%
     * <p>
     * i 表示行 j表示列
     * 右上方向移动时 i-- j++  (直到边界条件)
     * 左下方向移动时 i++ j--  (直到边界条件))
     * <p>
     * time O(mn)
     * space O(mn)
     *
     * @param matrix 输入矩阵
     * @return 对角线遍历结果
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null) {
            return null;
        }

        if (matrix.length == 0) {
            // 空数组
            return new int[]{};
        }

        // 行数
        int m = matrix.length;
        // 列数
        int n = matrix[0].length;

        // 一定会全部遍历到 所以有 mn个元素
        int[] result = new int[m * n];

        // 初始值
        result[0] = matrix[0][0];

        // 计数游标
        int count = 1;
        // 两个标识游标 i横向 j纵向
        int i;
        int j;

        // 如果超过2列 从第一行第2列的元素开始
        if (n > 1) {
            i = 0;
            j = 1;
        } else {
            // 如果只有1列 从第一列第2行的元素开始
            i = 1;
            j = 0;
        }

        // 开始对角线遍历
        while (count < m * n) {
            // 首先往左下走
            while (j >= 0 && i < m) {
                result[count++] = matrix[i][j];
                i++;
                j--;
            }

            // 到达边界后位置微调
            if (i == m) {
                i--;
                j += 2;
            } else {
                j += 1;
            }

            // j到0后 开始往右上走
            while (i >= 0 && j < n) {
                // j需要右移补充一位
                result[count++] = matrix[i][j];
                i--;
                j++;
            }

            // 到达边界后位置微调
            if (j == n) {
                j--;
                i += 2;
            } else {
                i += 1;
            }

        }
        return result;
    }
}
