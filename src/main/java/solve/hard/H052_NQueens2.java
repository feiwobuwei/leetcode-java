package solve.hard;

import org.junit.Test;

/**
 * N皇后问题的解决方案数
 *
 * @author minwei
 * @see H051_NQueens
 */
public class H052_NQueens2 {

    @Test
    public void test() {
        int i = totalNQueens(8);
        System.out.println(i);
    }

    private static int count;

    /**
     * 回溯法 -- 86.89%
     */
    public int totalNQueens(int n) {
        count = 0;
        // 保存最终摆放位置的一种结果 即每一行皇后的列位置 (肯定不会置于同一行)
        int[] array = new int[n];
        check(0, n, array);
        return count;
    }

    /**
     * 放置第n个皇后
     *
     * @param index 第n个皇后
     * @param n     总共要放置的数目
     * @param array 记录结果
     */
    private void check(int index, int n, int[] array) {
        // 递归终止条件
        if (index == n) {
            // 索引到了8 其实就是第9个棋子 此时代表已经找到了一个解
            count++;

            // 打印这个解
//            Arrays.stream(array).forEach((i) -> System.out.print((i + 1) + " "));
//            System.out.println();

            return;
        }

        for (int i = 0; i < n; i++) {
            // 每次都先把当前这个皇后 n 放到该行的第一列
            array[index] = i;
            // 判断是否冲突
            if (!isConflict(index, array)) {
                check(index + 1, n, array);
            }
            // 如果冲突 回溯到上一层 i++ 向右移动一列
        }
    }

    /**
     * 判断是否冲突 即是 同一列 同一斜线 (默认不置于同一行)
     * <p>
     *
     * @param n n表示第n个皇后 和前面n-1个皇后 比较
     * @return 冲突返回true 否则false
     */
    private boolean isConflict(int n, int[] array) {
        for (int i = 0; i < n; i++) {
            // 横向间距 Math.abs(array[n] - array[i]) 等于纵向间距 Math.abs(n - i) 即表示在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return true;
            }
        }
        // 不冲突
        return false;
    }
}
