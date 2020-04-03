package solve.hard;

import org.junit.Test;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1:
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * @author minwei
 */
public class H072_EditDistance {

    private String word1 = "bad";
    private String word2 = "apple";

    private static void printStringMatrix(String[][] u) {
        for (String[] row : u) {
            for (String element : row) {
                System.out.print(element + "\t\t");
            }
            System.out.println();
        }
    }

    @Test
    public void test() {
        int i = minDistance(word1, word2);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = minDistance2(word1, word2);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = minDistance3(word1, word2);
        System.out.println(i);
    }

    @Test
    public void test4() {
        String[][] path = new String[word1.length() + 1][word2.length() + 1];
        int i = minDistanceWithPath(word1, word2, path);
        System.out.println(i);

        printStringMatrix(path);
        System.out.println("========================================");
        printPath(path, word1, word2, word1.length(), word2.length());
    }

    @Test
    public void test5() {
        String word3 = "intention";
        String word4 = "execution";
        String[][] path = new String[word3.length() + 1][word4.length() + 1];
        int i = minDistanceWithPath(word3, word4, path);
        System.out.println(i);

        printPath(path, word3, word4, word3.length(), word4.length());
    }

    /*
    思路框架

    if s1[i] == s2[j]:
    跳过(skip）
    i, j 同时向前移动(-1) 次数不变

    else:
    三选一：
        插入（insert）j-1 次数+1
        删除（delete）i-1 次数+1
        替换（replace）i和j减1 次数+1

     */

    @Test
    public void test6() {
        String word3 = "我错了,我真的错了";
        String word4 = "我真的错了吗";
        String[][] path = new String[word3.length() + 1][word4.length() + 1];
        int i = minDistanceWithPath(word3, word4, path);
        System.out.println(i);

        printPath(path, word3, word4, word3.length(), word4.length());
    }

    /**
     * 递归 -- 提交超时
     * 存在重复子结构问题
     * 见下面两种优化方式
     */
    public int minDistance(String word1, String word2) {
        return minDistanceRec(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    private int minDistanceRec(String word1, String word2, int end1, int end2) {
        // 基准情形
        if (end1 == -1) {
            return end2 + 1;
        }
        if (end2 == -1) {
            return end1 + 1;
        }
        if (word1.charAt(end1) == word2.charAt(end2)) {
            return minDistanceRec(word1, word2, end1 - 1, end2 - 1);
        } else {
            return min3(
                    minDistanceRec(word1, word2, end1 - 1, end2),
                    minDistanceRec(word1, word2, end1, end2 - 1),
                    minDistanceRec(word1, word2, end1 - 1, end2 - 1)
            ) + 1;
        }
    }

    private int min3(int i, int j, int k) {
        int temp = (i < j) ? i : j;
        return temp < k ? temp : k;
    }

    /**
     * 动态规划-自底而上 -- 95.70%
     * <p>
     * 去重复子结构的方法1
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] distances = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            distances[i][0] = i;
        }
        for (int i = 0; i < n + 1; i++) {
            distances[0][i] = i;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 状态转移方程
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distances[i][j] = distances[i - 1][j - 1];
                } else {
                    // 状态转移方程
                    distances[i][j] = Math.min(distances[i - 1][j - 1],
                            Math.min(distances[i - 1][j], distances[i][j - 1])) + 1;
                }
            }
        }
        return distances[m][n];
    }

    /**
     * 备忘录递归-自顶而下 -- 99.96%
     * <p>
     * 去重复子结构的方法2
     */
    public int minDistance3(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        return minDistanceRecWithMemo(word1, word2,
                word1.length() - 1, word2.length() - 1, memo);
    }

    // ======================= 以下研究内容参考类注解的 @see LCS ===================== //

    private int minDistanceRecWithMemo(String word1, String word2, int end1, int end2, int[][] memo) {
        // 基准情形
        if (end1 == -1) {
            return end2 + 1;
        }
        if (end2 == -1) {
            return end1 + 1;
        }
        if (memo[end1][end2] != 0) {
            return memo[end1][end2];
        } else {
            if (word1.charAt(end1) == word2.charAt(end2)) {
                memo[end1][end2] = minDistanceRecWithMemo(word1, word2, end1 - 1, end2 - 1, memo);
                return memo[end1][end2];
            } else {
                memo[end1][end2] = min3(
                        minDistanceRecWithMemo(word1, word2, end1 - 1, end2, memo),
                        minDistanceRecWithMemo(word1, word2, end1, end2 - 1, memo),
                        minDistanceRecWithMemo(word1, word2, end1 - 1, end2 - 1, memo)
                ) + 1;
                return memo[end1][end2];
            }
        }
    }

    /**
     * 找出具体操作路径
     */
    public int minDistanceWithPath(String word1, String word2, String[][] path) {
        int m = word1.length();
        int n = word2.length();

        int[][] distances = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            distances[i][0] = i;
            path[i][0] = "删除";
        }
        for (int i = 0; i < n + 1; i++) {
            distances[0][i] = i;
            path[0][i] = "插入";
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 状态转移方程
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distances[i][j] = distances[i - 1][j - 1];
                    path[i][j] = "跳过";
                } else {
                    if (distances[i - 1][j - 1] < distances[i - 1][j] &&
                            distances[i - 1][j - 1] < distances[i][j - 1]) {
                        path[i][j] = "替换";
                    } else if (distances[i - 1][j] < distances[i][j - 1]) {
                        path[i][j] = "删除";
                    } else {
                        path[i][j] = "插入";
                    }
                    distances[i][j] = Math.min(distances[i - 1][j - 1],
                            Math.min(distances[i - 1][j], distances[i][j - 1])) + 1;
                }
            }
        }
        return distances[m][n];
    }

    /**
     * 按路径复原操作 从右下角开始
     *
     * @param path  操作路径
     * @param word1 被操作字符串
     * @param word2 参照字符串
     * @param i     被操作字符串的尾索引
     * @param j     参照字符串的尾索引
     */
    private void printPath(String[][] path, String word1, String word2, int i, int j) {
        int count = 1;

        while (i > 0 || j > 0) {
            if ("跳过".equals(path[i][j])) {
                System.out.printf("跳过 %s\n", word1.charAt(i - 1));
                i--;
                j--;
            } else if ("删除".equals(path[i][j])) {
                System.out.printf("第%d步 删除 %s\n", count++, word1.charAt(i - 1));
                i--;
            } else if ("插入".equals(path[i][j])) {
                System.out.printf("第%d步 插入 %s\n", count++, word2.charAt(j - 1));
                j--;
            } else {
                System.out.printf("第%d步 将 %s 替换为 %s \n", count++, word1.charAt(i - 1), word2.charAt(j - 1));
                i--;
                j--;
            }
        }

    }

}

