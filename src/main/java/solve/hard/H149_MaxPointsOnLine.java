package solve.hard;

import org.junit.Test;

/**
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * <pre>
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * </pre>
 *
 * @author minwei
 */
public class H149_MaxPointsOnLine {

    private int[][] points = {
            {1, 1},
            {2, 2},
            {3, 3}
    };


    @Test
    public void test() {
        int i = maxPoints(points);
        System.out.println(i);
    }


    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int result = 2;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int temp = 0;
                long x1 = points[i][0], y1 = points[i][1];
                long x2 = points[j][0], y2 = points[j][1];
                // 如果重复则(x2,y2)取和(x1,y1)不同的任意一点
                if (x1 == x2 && y1 == y2) {
                    x2++;
                    y2++;
                }
                // 遍历所有点，统计在直线上的点
                for (int[] point : points) {
                    long x = point[0];
                    long y = point[1];
                    if (((y - y1) * (x2 - x1) == (x - x1) * (y2 - y1))) {
                        temp++;
                    }
                }
                result = Math.max(result, temp);
            }
        }
        return result;

    }
}
