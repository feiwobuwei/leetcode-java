package solve.middle;

import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 *
 * @author minwei
 */
public class M079_WordSearch {

    private char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
    };

    @Test
    public void test() {
        boolean see = exist(board, "SEE");
        System.out.println(see);
    }

    @Test
    public void test2() {
        boolean see = exist(board, "ABCCED");
        System.out.println(see);
    }

    /**
     * 回溯法 -- 93.50%
     * <p>
     * time O(mn)
     * space O(mn)
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) {
            return word.length() == 0;
        }
        int n = board[0].length;
        // 访问标记
        boolean[][] flag = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    flag[i][j] = true;
                    if (exist(board, word, 1, i, j, flag)) {
                        return true;
                    }
                    flag[i][j] = false;
                }
            }
        }
        return false;
    }

    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * 在位置(x, y)处，需要寻找word中索引为index的字符
     *
     * @param board 二维字符数组
     * @param word  要找的单词
     * @param index word的index索引
     * @param x     坐标 上下移动
     * @param y     坐标 左右移动
     * @param flag  访问标记
     * @return 存在该字符返回true 否则false
     */
    private boolean exist(char[][] board, String word, int index, int x, int y, boolean[][] flag) {
        if (index == word.length()) {
            return true;
        }
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            // 先确保不越界
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                // 回溯过程
                if (!flag[newX][newY] && board[newX][newY] == word.charAt(index)) {
                    flag[newX][newY] = true;
                    if (exist(board, word, index + 1, newX, newY, flag)) {
                        return true;
                    }
                    flag[newX][newY] = false;
                }
            }
        }
        return false;
    }

}
