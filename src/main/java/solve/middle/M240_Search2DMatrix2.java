package solve.middle;

import org.junit.Test;

/**
 * 搜索二维矩阵2
 * <p>
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * 示例:
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * <p>
 *
 * @author minwei
 * @see M074_Search2DMatrix
 */
public class M240_Search2DMatrix2 {

    private int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
    };

    @Test
    public void test() {
        boolean b = searchMatrix(matrix, 20);
        System.out.println(b);
    }

    @Test
    public void test2() {
        boolean b = searchMatrix2(matrix, 20);
        System.out.println(b);
    }

    @Test
    public void test3() {
        boolean b = searchMatrix3(matrix, 20);
        System.out.println(b);
    }

    /**
     * 暴力法 -- 80.17%
     * <p>
     * time O(mn)
     * space O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (0 == m) {
            return false;
        }
        int n = matrix[0].length;
        if (0 == n) {
            return false;
        }
        for (int[] matrix1 : matrix) {
            for (int j = 0; j < n; j++) {
                if (target == matrix1[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 行二分法 - 99.10%
     * <p>
     * time O(m*logn)
     * space O(m)
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        for (int[] matrix1 : matrix) {
            if (binarySearch(matrix1, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == array[mid]) {
                return true;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    /**
     * 从右上角 开始行走
     * <p>
     * time O(m+n) 最差情况在左下角
     * space O(1)
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        int m = matrix.length;
        if (0 == m) {
            return false;
        }
        int n = matrix[0].length;
        if (0 == n) {
            return false;
        }
        // 先位于右上角
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            // 小就往左走
            if (target < matrix[row][col]) {
                col--;
                // 大就往下走
            } else if (target > matrix[row][col]) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }


}
