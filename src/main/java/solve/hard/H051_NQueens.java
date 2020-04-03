package solve.hard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题的解决方案布局
 * <p>
 * 输入: 4
 * 输出:
 * <pre>
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * </pre>
 *
 * @author minwei
 * @see H052_NQueens2
 */
public class H051_NQueens {

    @Test
    public void test() {
        List<List<String>> lists = solveNQueens(8);
        lists.forEach((i) -> {
            i.forEach(System.out::println);
            System.out.println("=============");
        });
        System.out.println(lists.size());
    }


    /**
     * 回溯法 --98.40%
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] array = new int[n];
        check(0, n, array, result);
        return result;
    }

    /**
     * 放置第n个皇后
     *
     * @param index  第n个皇后
     * @param n      总共要放置的数目
     * @param array  记录列的情况
     * @param result 最终返回结果
     */
    private void check(int index, int n, int[] array, List<List<String>> result) {
        // 递归终止条件
        if (index == n) {
            List<String> temp = new ArrayList<>();
            for (int ele : array) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (i == ele) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                temp.add(sb.toString());
            }
            result.add(temp);
            return;
        }

        for (int i = 0; i < n; i++) {
            // 每次都先把当前这个皇后 n 放到该行的第一列
            array[index] = i;
            // 判断是否冲突
            if (!isConflict(index, array)) {
                check(index + 1, n, array, result);
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
