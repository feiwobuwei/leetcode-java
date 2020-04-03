package solve.easy;

/**
 * 买卖股票的最好时机(二)
 * 可以多次交易
 *
 * @author minwei
 */
public class E122_MaxProfit2 {

    /**
     * 峰谷法
     * <p>
     * time O(n) space O(1)
     *
     * @param prices 股价数组
     * @return 最大利润
     */
    public static int maxProfit(int[] prices) {
        int i = 0;
        int valley;
        int peak;
        // 记录最大利润
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            // 先找到谷
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            // 再找峰
            peak = prices[i];
            // 本次交易利润
            maxprofit += peak - valley;
        }
        return maxprofit;
    }

    /**
     * 单次遍历 one-pass
     * <p>
     * time O(n) space O(1)
     */
    public static int maxProfit2(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        int[] test = {7, 1, 5, 3, 6, 4};
        int result = maxProfit(test);
        System.out.println(result);

        int result2 = maxProfit2(test);
        System.out.println(result2);
    }

}
