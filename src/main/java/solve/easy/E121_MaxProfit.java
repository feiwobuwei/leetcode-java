package solve.easy;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * @author minwei
 */
public class E121_MaxProfit {

    /**
     * 暴力法
     * <p>
     * time O(n^2) space O(1)
     *
     * @param prices 股价数组
     * @return 最大利润
     */
    public static int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    /**
     * 峰谷法
     * <p>
     * one-pass 单次遍历,维持两个变量——minprice 和 maxprofit，
     * 分别对应迄今为止所得到的最小的谷值和最大的利润（卖出价格与最低价格之间的最大差值）。
     * <p>
     * time O(n) space O(1)
     */
    private static int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 先假定第一个价格最低
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // 如果出现了更低价格 替换掉并直接进入下次循环
            // 抛弃掉以前的计算结果 因为用这个价格计算获得的必然不是最大利润
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else if (maxProfit < prices[i] - minPrice) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }


    /**
     * DP
     * 根据牛顿—莱布尼茨公式
     * 区间和可以转换成求差的问题，求差问题，也可以转换成区间和的问题。
     * 该问题转为求 最大连续子数组和(可参考E_053)
     * <p>
     * time O(n) space O(1)
     */
    private static int maxProfit3(int[] prices) {

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // dp[i] 表示以 i 为结尾的最大连续子数组和
        // n个元素有n-1个差值
        int[] dp = new int[prices.length - 1];
        dp[0] = (prices[1] - prices[0] < 0) ? 0 : prices[1] - prices[0];

        // res用于记录dp[]数组中的最大值 初始值赋值为 dp[0];
        int res = dp[0];

        for (int i = 1; i < prices.length - 1; i++) {
            dp[i] = (prices[i + 1] - prices[i]) + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
            res = Math.max(res, dp[i]);

        }
        return res;
    }

    public static void main(String[] args) {
//        int[] test = {7, 1, 5, 3, 6, 4};
//
//        int result = maxProfit(test);
//        System.out.println(result);
//
//        int result2 = maxProfit2(test);
//        System.out.println(result2);

        int[] test2 = {1, 2, 4};
        int result3 = maxProfit3(test2);
        System.out.println(result3);

    }

}
