package solve.hard;

import org.junit.Test;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * @author minwei
 * @see solve.middle.M036_ValidSudoku
 */
public class H037_SudokuSolver {

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
        solveSudoku(board);
        printBoard(board);
    }

    /**
     * DFS
     * 36题的扩展
     *
     * @param board 待填充数独
     */
    public void solveSudoku(char[][] board) {

        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 九宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];

        // 先把 不是'.' 的数字的备忘录位置都置为true
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    row[i][num] = true;
                    col[j][num] = true;
                    block[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        dfs(board, row, col, block, 0, 0);
    }

    /**
     * 辅助方法 递归 + 回溯
     *
     * @param board 数独
     * @param row   行备忘录
     * @param col   列备忘录
     * @param block 块备忘录
     * @param i     待填入位置的行索引
     * @param j     待填入位置的列索引
     * @return 如果没有填过该数字
     */
    private boolean dfs(char[][] board, boolean[][] row, boolean[][] col,
                        boolean[][] block, int i, int j) {
        // 找寻不是'.'的位置
        while (board[i][j] != '.') {
            if (++j > 8) {
                i++;
                j = 0;
            }
            if (i > 8) {
                return true;
            }
        }

        for (int num = 0; num <= 8; num++) {
            int blockIndex = i / 3 * 3 + j / 3;
            if (!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
                // 递归
                board[i][j] = (char) ('1' + num);
                row[i][num] = true;
                col[j][num] = true;
                block[blockIndex][num] = true;
                if (dfs(board, row, col, block, i, j)) {
                    return true;
                } else {
                    // 回溯
                    row[i][num] = false;
                    col[j][num] = false;
                    block[blockIndex][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }


    /**
     * 辅助打印方法
     */
    private void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
