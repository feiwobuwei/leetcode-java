package solve.middle;

import org.junit.Test;

/**
 * 搜索二维矩阵
 * <p>
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * <p>
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 *
 * @author minwei
 * @see M240_Search2DMatrix2
 */
public class M074_Search2DMatrix {

    private int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 50},
    };

    @Test
    public void test() {
        boolean b = searchMatrix(matrix, 11);
        System.out.println(b);
    }

    @Test
    public void test2() {
        int[] nums = {1, 3};
        int floor = floor(nums, 3);
        System.out.println(floor);
    }

    /**
     * 两次二分法 - 100%
     * <p>
     * 先对每行第一列的数据用floor函数进行查找。
     * 再对找到的那一行进行二分查找法。
     * <p>
     * 时间复杂度是O(log(m) + log(n))
     * 空间复杂度是O(m)。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return false;
        }
        // 拷贝第一列
        int[] array = new int[m];
        for (int i = 0; i < m; i++) {
            array[i] = matrix[i][0];
        }
        int row = floor(array, target);
        if (row == -1) {
            return false;
        }
        return binarySearch(matrix[row], target);
    }

    /**
     * 对第一列的二分查找
     *
     * @param array  矩阵每一行都是一个数组
     * @param target 目标值
     * @return target所在行。如果超出行数返回-1
     */
    private int floor(int[] array, int target) {
        int left = -1, right = array.length - 1;
        while (left < right) {
            // 左闭右开
            int mid = left + ((right - left + 1) >> 1);
            if (array[mid] < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (left + 1 < array.length && array[left + 1] == target) {
            return left + 1;
        }
        return left;
    }

    /**
     * 对target所在行的二分查找
     */
    private boolean binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        // O(log n)
        while (left <= right) {
            int mid = left + ((right - left) >> 2);
            if (array[mid] == target) {
                return true;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

}
