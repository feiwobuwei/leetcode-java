package solve.middle;

import org.junit.Test;

import java.util.*;

/**
 * 合并区间
 * <p>
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @author minwei
 */
public class M056_MergeIntervals {

    int[][] nums = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
    };

    @Test
    public void test() {
        int[][] merge = merge(nums);
        System.out.println(Arrays.deepToString(merge));
    }

    @Test
    public void test2() {
        int[][] merge = merge2(nums);
        System.out.println(Arrays.deepToString(merge));
    }

    /**
     * 区间类
     */
    private class Pair {
        int left;
        int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 排序 -- 44.57%
     * <p>
     * time O(n * logn)
     * space O(n)
     */
    public int[][] merge(int[][] intervals) {
        int n;
        if (intervals == null || (n = intervals.length) == 0 || n == 1) {
            return intervals;
        }

        // 根据区间左值从小到大顺序排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            // 如果后一个区间的左值小于等于本区间的右值，可以将这两个区间合并
            if (intervals[i][1] >= intervals[i + 1][0]) {
                // 将合并结果存储在后一个区间中，这样下一个遍历的区间就是这个合并后的区间

                // 因为之前对intervals数组根据区间左值由小到大进行排序，
                // 所以合并后新区间的左值一定是当前区间的左值
                intervals[i + 1][0] = intervals[i][0];
                // 但是，合并后新区间的右值需要取当前区间和后一个区间的右值的最大值
                intervals[i + 1][1] = Math.max(intervals[i + 1][1], intervals[i][1]);
            } else {
                // 如果不能合并，将当前区间的信息存储进list结果集里
                list.add(new Pair(intervals[i][0], intervals[i][1]));
            }
            // 如果i == n - 2，那么不管第n - 2个区间是否能与第n - 1个区间合并，
            // 我们都需要将第n - 1个区间置入结果集中
            if (i == n - 2) {
                list.add(new Pair(intervals[n - 1][0], intervals[n - 1][1]));
            }
        }
        // 将list转换成数组形式返回
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = list.get(i).left;
            result[i][1] = list.get(i).right;
        }
        return result;
    }

    // ========================= 并查集 Union-Find ============================ //

    private int[] parent;

    /**
     * 并查集 -- 5.36%
     * 但空间是 96.70%
     * <p>
     * 时间复杂度是O(n^2)
     * 空间复杂度是O(n)
     */
    public int[][] merge2(int[][] intervals) {
        // 二维数组的行数 也就是区间(线段)数
        int n;
        if (intervals == null || (n = intervals.length) == 0 || n == 1) {
            return intervals;
        }
        // 每个索引的元素表示的是该区间的根
        parent = new int[n];
        // 初始化根都是自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 第一个区间的左值大于等于第二个区间的左值  且
                // 第一个区间的左值小于等于第二个区间的右值
                // [3 6] [1 4] 如果第二个也是大于([1 2])则完全不相交
                boolean b1 = (intervals[i][0] >= intervals[j][0] &&
                        intervals[i][0] <= intervals[j][1]);

                // 第一个区间的左值小于等于第一个区间的左值 且
                // 第一个区间的右值大于等于第二个区间的左值
                // [3 6] [4 7] 如果第二个也是小于([7 9])则完全不相交
                boolean b2 = (intervals[i][0] <= intervals[j][0] &&
                        intervals[i][1] >= intervals[j][0]);

                // 这2中情形需要进行合并处理 将i区间并入j区间
                if (b1 || b2) {
                    union(i, j);
                }

            }
        }

        // 结果处理
        Set<Integer> parentSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            parentSet.add(findParent(i));
        }

        int[][] result = new int[parentSet.size()][2];
        int index = 0;

        for (Integer cursor : parentSet) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (findParent(i) == cursor) {
                    max = Math.max(max, intervals[i][1]);
                    min = Math.min(min, intervals[i][0]);
                }
            }
            result[index][0] = min;
            result[index][1] = max;
            index++;
        }
        return result;
    }

    private int findParent(int element) {
        while (element != parent[element]) {
            // 路径压缩
            parent[element] = parent[parent[element]];
            element = parent[element];
        }
        return element;
    }

    private void union(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);

        if (aParent != bParent) {
            // 将第一个并入第二个
            parent[aParent] = bParent;
        }
    }

}
