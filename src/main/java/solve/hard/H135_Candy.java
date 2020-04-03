package solve.hard;

import org.junit.Test;

/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * <p>
 * 示例 2:
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * @author minwei
 */
public class H135_Candy {


    @Test
    public void test() {
        // 2 1 1 2 1 2 3
        int[] rates = {3, 1, 1, 2, 0, 1, 2};
        int candy = candy(rates);
        System.out.println(candy);
    }

    /**
     * 贪心算法 -- 99.53%
     * <p>
     * time O(n)
     * space O(n)
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        for (int i = 0; i < n; i++) {
            // 每人至少发一颗糖
            candies[i] = 1;
        }

        // 从前往后遍历ratings数组
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // 从后往前遍历ratings数组
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += candies[i];
        }
        return sum;
    }
}
