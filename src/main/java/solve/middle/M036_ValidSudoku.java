package solve.middle;

import org.junit.Test;

/**
 * 有效的数独
 * <p>
 * 说明:
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 *
 * @author minwei
 * @see solve.hard.H037_SudokuSolver
 */
public class M036_ValidSudoku {

    private char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    @Test
    public void test() {
        boolean validSudoku = isValidSudoku(board);
        System.out.println(validSudoku);
    }

    /**
     * 类似 DFS
     * 使用三个访问标志二维数组
     * <p>
     * time O(1)  -- 81格
     * space O(1) -- 81格 * 3
     */
    public boolean isValidSudoku(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 九宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // 减'1'是因为后面要取的num是索引
                    int num = board[i][j] - '1';
                    // 该元素所在九宫格的索引
                    int blockIndex = i / 3 * 3 + j / 3;
                    // 检查该元素所在的行列块是否已经有该元素
                    // 任意一个条件为true 则直接短路返回false
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        // 若全部都没有 则该元素OK 将三个访问记录标志均置为true
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }
}
