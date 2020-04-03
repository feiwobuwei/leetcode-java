package solve.middle;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，
 * 计算岛屿的数量。一个岛被水包围，
 * 并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 * <p>
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 *
 * @author minwei
 * @see M547_FriendCircles
 */
public class M200_NumberOfIslands {

    private char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}

    };

    @Test
    public void test1() {
        int i = numIslands(grid);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = numIslands2(grid);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = numIslands3(grid);
        System.out.println(i);
    }

    /**
     * DFS -- 99.03%
     * 递归
     * <p>
     * 时间复杂度 O(mn)
     * 空间复杂度 O(mn)
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    infect(grid, i, j, m, n);
                }
            }
        }
        return res;
    }

    /**
     * 感染函数
     */
    private void infect(char[][] grid, int i, int j, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        infect(grid, i + 1, j, row, col);
        infect(grid, i - 1, j, row, col);
        infect(grid, i, j + 1, row, col);
        infect(grid, i, j - 1, row, col);
    }

    /**
     * 不使用 javafx.util.Pair
     */
    private class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * BFS -- 35.51%
     * 迭代
     * <p>
     * 时间复杂度 O(mn)
     * 空间复杂度 O(mn)
     */
    public int numIslands2(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int count = 0;

        // BFS 用队列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    Queue<Pair> queue = new LinkedList<>();
                    queue.add(new Pair(i, j));
                    // 将遍历过的节点标记为'2'
                    grid[i][j] = '2';
                    while (!queue.isEmpty()) {
                        Pair pair = queue.poll();
                        for (int[] ints : directions) {
                            int newX = pair.x + ints[0];
                            int newY = pair.y + ints[1];
                            if (newX >= 0 && newX < m && newY >= 0 && newY < n &&
                                    grid[newX][newY] == '1') {
                                queue.add(new Pair(newX, newY));
                                grid[newX][newY] = '2';
                            }
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }


    private int[] parent;

    /**
     * 并查集 -- 30.84%
     * <p>
     * time O(mn)
     * space O(mn)
     */
    public int numIslands3(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        // 转为一维数组 并 初始化
        parent = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = i * n + j;
                if (grid[i][j] == '1') {
                    parent[tmp] = tmp;
                } else {
                    parent[tmp] = -1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int newX = i + directions[k][0];
                        int newY = j + directions[k][1];
                        if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == '1') {
                            union(i * n + j, newX * n + newY);
                        }
                    }
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1) {
                continue;
            }
            int father = find(i);
            set.add(father);
        }
        return set.size();
    }

    private int find(int x) {
        while (x != parent[x]) {
            // 路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];

        }
        return x;
    }

    private void union(int a, int b) {
        int aFather = find(a);
        int bFather = find(b);
        if (aFather != bFather) {
            parent[aFather] = bFather;
        }
    }

}
